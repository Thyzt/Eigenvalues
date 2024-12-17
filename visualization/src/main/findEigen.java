package main;

public class findEigen {
    private static double[][] origMatrix;
    private static double lambda;
    private static double a, b, c, d;

    public static double[] findLambda(double[][] matrix){ // finds the eigenvalue
        origMatrix = matrix;
        a = origMatrix[0][0]; b = origMatrix[0][1]; c = origMatrix[1][0]; d = origMatrix[1][1];
        
        double[] lambda = new double[2];
        lambda[0] = (a+d - Math.sqrt((a+d)*(a+d) - 4 * (a * d - b * c)))/2.0;
        lambda[1] = (a+d + Math.sqrt((a+d)*(a+d) - 4 * (a * d - b * c)))/2.0;
        return lambda;
    }

    public static double[][] findVector(double[] lambda){ // finds both spanning vectors
        double[][] newMatrix = origMatrix;
        double[][] finalMatrix = new double[2][2];

        newMatrix[0][0] -= lambda[0];
        newMatrix[1][1] -= lambda[0];
        
        //todo: find RREF of this matrix
        // solutions are the new vector
        
        finalMatrix[0][0] = 0;//=solution1 x1;
        finalMatrix[1][0] = 0;//=solution1 x2;

        newMatrix = origMatrix;
        newMatrix[0][0] -= lambda[1];
        newMatrix[1][1] -= lambda[1];

        //todo: find RREF of this matrix
        // solutions are the new vector
        
        finalMatrix[0][1] = 0;//=solution2 x1;
        finalMatrix[1][1] = 0;//=solution2 x2;
        
        return finalMatrix;
    }

    public static double[] solutionsRREF(double[][] matrix){
        
        double[] vectors = {0.0, 0.0};
        if (matrix[0][0] != 0.0 && matrix[1][0] != 0.0){
            double multiple = matrix[1][0] / matrix[0][0];
            matrix[0][0]*=multiple;
            matrix[0][1]*=multiple;
            vectors[0]*=multiple;
            matrix[1][0]-=matrix[0][0];
            matrix[1][1]-=matrix[0][1];
            vectors[1]-=vectors[0];
            multiple = matrix[0][1] / matrix[1][1];
            //matrix
        }
        return vectors;
    }

    
    
}