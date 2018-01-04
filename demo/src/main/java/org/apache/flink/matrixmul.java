package org.apache.flink;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.CsvReader;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.omg.CORBA.INTERNAL;

public class matrixmul {
    public static void run() throws Exception{
        //1. get env
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        //2. get data
        DataSource<Tuple3<Integer,Integer,Integer>> matrixA=readSparseMatrix(env,Config.MATRIX_A);
        DataSource<Tuple3<Integer,Integer,Integer>> matrixB=readSparseMatrix(env, Config.MATRIX_B);
        //3.tranformation
        DataSet<Tuple3<Integer,Integer,Integer>> resultmatrix = matrixA.join(matrixB)
                .where(1).equalTo(0)
                .map(new multiplication()).groupBy(0,1).sum(2);

        //4.sink (can simply use print)
        resultmatrix.print();
        //5.execute
        env.execute();
    }

    public static DataSource<Tuple3<Integer, Integer, Integer>> readSparseMatrix(ExecutionEnvironment env,
                                                                                 String filePath) {
        CsvReader csvReader = env.readCsvFile(filePath);
        csvReader.fieldDelimiter(',');
        //csvReader.includeFields("fttt");

        return csvReader.types(Integer.class, Integer.class, Integer.class);
    }

    public static final class multiplication implements MapFunction<Tuple2<Tuple3<Integer,Integer,Integer>,Tuple3<Integer,Integer,Integer>>,Tuple3<Integer,Integer,Integer>> {
        @Override
        public Tuple3<Integer, Integer, Integer> map(Tuple2<Tuple3<Integer, Integer, Integer>, Tuple3<Integer, Integer, Integer>> value) throws Exception {
            Integer row=value.f0.f0;
            System.out.println(row);
            Integer column=value.f1.f1;
            System.out.println(column);

            Integer product=value.f0.f2 * value.f1.f2;
            System.out.println(product);
            return new Tuple3<Integer, Integer, Integer>(row,column,product);
        }
    }




    public static void main(String[] args) throws Exception{
        new matrixmul().run();
    }
}
