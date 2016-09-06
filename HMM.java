public class HMM {
	public static void main(String args[]){
		double [][]a = {{0.5,0.2,0.3},{0.3,0.5,0.2},{0.2,0.3,0.5}};
		double [][]b = {{0.5,0.5},{0.4,0.6},{0.7,0.3}};
		double []pi = {0.2,0.4,0.4};
		int []observation = {0,1,0};
		double result = forward(a,b,pi,observation);
		System.out.println(result);
	}
	
	/**
	 * 
	   * @Name: forward 前向传播算法 ，预测观测序列(暂不考虑隐含状态)
	   * @Description: @param a 状态转移概率矩阵
	   * @Description: @param b 观测概率矩阵
	   * @Description: @param pi 初始状态概率向量
	   * @Description: @param observation 观测序列
	 */
	public static double forward(double [][]a,double [][]b,double []pi,int []observation){
		//存储临时数据，每行为一个观察序列节点的所有可能性
		double [][] data = new double[observation.length][pi.length];
		
		for (int i = 0; i < observation.length; i++) {
			if(i == 0){
				for (int j = 0; j < pi.length; j++) {
						data[i][j] = pi[j]*b[j][observation[i]];
						System.out.println(data[i][j]);
				}
			}
			else {
				for (int j = 0; j < pi.length; j++) {
					double temp = 0;
					for (int k = 0; k < pi.length; k++) {
						temp = temp + data[i-1][k]*a[k][j];
					}
					data[i][j] = temp*b[j][observation[i]];
					System.out.println(data[i][j]);
					}
				}

			}

		double result = 0;
		for (int i = 0; i < data[data.length-1].length; i++) {
			result += data[data.length-1][i];
		}
		return result;
	}
}
