#include <stdio.h>
#include <stdlib.h>
#include <time.h>
void create_marks_csv(char *filename,int a[][100],int n,int m){
 
printf("\n Creating %s.csv file",filename);
 
FILE *fp;
 
int i,j;
 
filename=strcat(filename,".csv");
 
fp=fopen(filename,"w+");
 
for(i=0;i<m;i++){
 
    //fprintf(fp,"\n%d",i+1);
 	//fprintf(fp,"\n");
    for(j=0;j<n;j++)
 
        //fprintf(fp,",%d ",a[i][j]);
 		fprintf(fp,"%d,%d,%d\n",i,j,a[i][j]);

    }
 
fclose(fp);
 
printf("\n %sfile created",filename);
 
}
int main(){
	int row=100;
	int col=100;
	int matrix[row][col];
	srand( time(NULL));
	for(int i=0;i<row;i++){
		for(int j=0;j<col;j++){
			int r=rand()%10+1;
			matrix[i][j]=r;
		}
	}
	char str[100];
	gets(str);
	create_marks_csv(str,matrix,100,100);
	return 0;
}
