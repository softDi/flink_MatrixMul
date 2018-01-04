package org.apache.flink;

import scala.util.parsing.combinator.testing.Str;

public class Config {
    public static final String DIR="/root/matrix/";//path to your matrix dir 
    public static final String MATRIX_A = DIR +"a.csv";
    public static final String MATRIX_B =DIR+"b.csv";
    public static String matrix;
    public Config(String filename){
        matrix=DIR+filename+".csv";
    }
}
