import java.io.*;
import java.sql.SQLOutput;

public class Assignment04_20220808056 {
    public static void main(String[] args) throws IOException {
        if (args.length==1|| args.length==4){
            String baseFileName = args[0];
            String courseDetailsFileName = (args.length == 4) ? args[0] + ".txt" : baseFileName + "_CourseDetails.txt" ;
            String studentScoresFileName = (args.length == 4) ? args[1] + ".txt" : baseFileName + "_StudentScores.txt";
            String studentGradesFileName = (args.length == 4) ? args[2] + ".txt": baseFileName + "_StudentGrades.txt";
            String errorLogFileName = (args.length == 4)      ? args[3] + ".log": baseFileName + "_Errors.log";

            String[] studentNames = readStudentNames(studentScoresFileName);
            double[] grades = readGrades(studentScoresFileName);
            writeGrades(studentNames, grades, studentGradesFileName, errorLogFileName);
        } else {
            writeErrorToLog("ERROR: ");
        }
        }
    public static String[] readStudentNames(String fileName) {
        try (BufferedReader bReader = new BufferedReader(new FileReader(fileName))) {
            int count = countCategory(fileName);
            String[] studentNames = new String[count];
            int index = 0;
            String line;
            while ((line = bReader.readLine()) != null && index < count) {
                studentNames[index] = line.trim();
                index++;
            }
            return studentNames;
        } catch (IOException e) {
            writeErrorToLog("ERROR: IO EXCEPTION");
            return new String[0];
        }
    }
    private static void writeErrorToLog(String errorMessage) {
        try (BufferedWriter errorLogWriter = new BufferedWriter(new FileWriter("error.log", true))) {
            errorLogWriter.write(errorMessage + "\n");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }public static double[] readGrades(String fileName) {
        try (BufferedReader bReader = new BufferedReader(new FileReader(fileName))) {
            int count = countCategory(fileName);
            double[] grades = new double[count];
            int index = 0;
            String line;
            while ((line = bReader.readLine()) != null && index < count) {
                grades[index] = Double.parseDouble(line.trim());
                index++;
            }

            return grades;
        } catch (IOException | NumberFormatException e) {
            System.out.println("ERROR IO EXCEPTION");
            writeErrorToLog("ERROR:  ");
            return new double[0];
        }}

    public static int countCategory(String fileName)throws IOException {
        FileReader fReader=new FileReader(fileName);
        int count=0;
        String line;
        BufferedReader bReader=new BufferedReader(fReader);
        while ((line=bReader.readLine()) !=null){
            if (!line.trim().isEmpty()) { //////7
                count++;
        }}
        return count;
    }
    public static void getCategory(String[] category,int[] quantity,int[] weight,String fileName) throws IOException{

        int index=0;
        String line;
        try ( FileReader fReader=new FileReader(fileName);
              BufferedReader bReader=new BufferedReader(fReader)){
            while ((line=bReader.readLine())!=null&&index<category.length){

                String[] parts = line.split("\\s+");

                if (parts.length == 3) {
                    category[index] = parts[0];
                    quantity[index] = Integer.parseInt(parts[1]);
                    weight[index] = Integer.parseInt(parts[2]);
                    index++;
                } else {
                    System.out.println("ERROR: Invalid line: " + line);
                }
            }
        }
    }
    public static void writeGrades(String[] student, double[] grades, String studentGrades, String errorLog) throws IOException {
        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(studentGrades));
             BufferedWriter errorLogWriter = new BufferedWriter(new FileWriter(errorLog))) {
            if (student.length != grades.length) {
                errorLogWriter.write("ERROR: Number of students and grades are not same");
                return;
            }

            for (int i = 0; i < student.length; i++) {
                String studentName = student[i];
                double gradeVal = grades[i];
                if (gradeVal < 0 || gradeVal > 100) {
                    errorLogWriter.write("ERROR: Invalid grade for " + studentName + "\n");
                    continue;
                }
                String gradeLetter=gradeLetter(gradeVal);
                double gpaPoints=gpaPoints(gradeVal);
                String status=status(gradeVal);
                String outputLine = String.format("%s %.2f %s %.2f %s%n", studentName, gradeVal, gradeLetter, gpaPoints, status);
                bWriter.write(outputLine);
        }
        }

    }     public static double gpaPoints(double grade) {
        if (grade<=100&&grade>=88){
            return 4.0;
        }else if (grade>=81&&grade<=87){
            return  3.5;
        }else if (grade>=74&&grade<=80){
            return 3;
        }else if (grade>=67&&grade<=73){
            return  2.5;
        }else if (grade>=60&&grade<=66){
            return  2;
        }else if (grade>=53&&grade<=59){
            return  1.5;
        }else if (grade>=46&&grade<=52){
            return  1.0;
        }else if (grade>=35&&grade<=45){
            return  0.5;
        }else return  0.0;}

    public static String status(double grade) {
        if (grade<=100&&grade>=60) {
            return ("passed");
        }else if (grade>=46&&grade<=59){
            return  ("conditionally passed");
        }else return  ("failed");
    }
    public static String gradeLetter(double grade) {
                if (grade <= 100 && grade >= 88) {
                    return ("AA");
                } else if (grade >= 81 && grade <= 87) {
                    return ("BA");
                } else if (grade >= 74 && grade <= 80) {
                    return ("BB");
                } else if (grade >= 67 && grade <= 73) {
                    return ("BC");
                } else if (grade >= 60 && grade <= 66) {
                    return ("CC");
                } else if (grade >= 53 && grade <= 59) {
                    return ("DC");
                } else if (grade >= 46 && grade <= 52) {
                    return ("DD");
                } else if (grade >= 35 && grade <= 45) {
                    return ("FD");
                } else if (grade >= 0 && grade <= 34) {
                    return ("FF");
                } else {
                    return ("Something went wrong");
                }
            }

        }
