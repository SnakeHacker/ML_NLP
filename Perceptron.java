package com.test;

public class perceptron {
	public static void main(String args[]){
		double [][]datax = {{1,2,3,4},{1,2,3,5},{2,2,3,4},{3,2,3,4}};
		int []datay = {1,1,-1,-1};
		double []w = {0,0,0,0};
		double []result = perceptron(0,w,0.5,datax,datay);
		for (int i = 0; i<result.length; i++){
			System.out.println(result[i]);
		}
		System.out.println(predict(result, new double[]{1,2,3,4}));
	}
	
/**
 * 
   * @Name: perceptron 感知机
   * @Description: @param b 初始偏置
   * @Description: @param w 初始权重
   * @Description: @param ls 学习速率
   * @Description: @param datax 训练集X
   * @Description: @param datay X对应的Y
   * @Description: @return 返回一个参数列表result  result为(w0,w1,...wn,b)
 */
	public static double[] perceptron (double b , double w[], double ls, double [][]datax, int []datay){
		double []parameter = new double[w.length+1];
		double temp = 0;
		int flag = 0;	//循环结束标志
		int number = 0;
		while (true) {
			if (flag == 2 ){
				break;
			}
			System.out.println("第"+ (number++) +"次循环");
			//第一次，遍历训练集
			for (int i = 0; i<datax.length; i++){
				//数据与权重相乘
				for (int j = 0 ; j < datax[i].length ; j++){
					 temp += datax[i][j]*w[j];
				}
			    temp = temp + b;
			    //判定是否为误分类
				if (temp*datay[i] < 0 || temp*datay[i] == 0){
					flag = 1;
			    	for (int k = 0 ; k <w.length ; k++){
			    		w[k] = w[k]+ls*datay[i]*datax[i][k];
			    	}
			    	b = b +ls*datay[i];
				}else if (i == datax.length-1) {
					flag = 2;
				}
			}
		}
		
		for (int i = 0; i < parameter.length; i++) {
			if (i < parameter.length-1){
				parameter[i] = w[i];
			}else {
				parameter[i] = b;
			}
		}
		return parameter;
	}

/**
 * 
   * @Name: predict
   * @Description: @param parameter 参数w,b
   * @Description: @param x 输入测试
   * @Description: @return 返回测试结果 0在平面上,1 正例,-1 负例
 */
	public static int predict(double []parameter,double []x){
		int result = 0;
		int temp = 0;
		for (int i = 0; i < x.length; i++) {
			temp += x[i]*parameter[i];
		}
		temp += parameter[parameter.length-1];
		if (temp > 0 ){
			result = 1;
		}
		else if (temp < 0){
			result = -1;
		}else if (temp == 0) {
			result = 0;
		}
		return result;
	}
}
