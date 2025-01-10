import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StudentMarksCalculator extends JFrame {
    private JTextField[] marksFields;
    private JButton calculateButton;
    private JLabel totalLabel, percentageLabel, gradeLabel;

    public StudentMarksCalculator(int numSubjects) {
        setTitle("Marks Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(numSubjects + 4, 2));

        marksFields = new JTextField[numSubjects];
        
        for (int i = 0; i < numSubjects; i++) {
            add(new JLabel("Marks for Subject " + (i + 1) + " (out of 100):"));
            marksFields[i] = new JTextField();
            add(marksFields[i]);
        }

        calculateButton = new JButton("Calculate Results");
        calculateButton.addActionListener(new CalculateButtonListener());
        add(calculateButton);

        totalLabel = new JLabel("Total Marks: ");
        add(totalLabel);

        percentageLabel = new JLabel("Average Percentage: ");
        add(percentageLabel);

        gradeLabel = new JLabel("Grade: ");
        add(gradeLabel);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int totalMarks = 0;
            int numSubjects = marksFields.length;
            boolean validInput = true;

            for (JTextField markField : marksFields) {
                try {
                    int mark = Integer.parseInt(markField.getText());
                    if (mark < 0 || mark > 100) {
                        JOptionPane.showMessageDialog(null, "Please enter marks between 0 and 100.");
                        validInput = false;
                        break;
                    }
                    totalMarks += mark;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid marks.");
                    validInput = false;
                    break;
                }
            }

            if (validInput) {
                double averagePercentage = (double) totalMarks / numSubjects;
                String grade = calculateGrade(averagePercentage);

                totalLabel.setText("Total Marks: " + totalMarks);
                percentageLabel.setText("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
                gradeLabel.setText("Grade: " + grade);
            }
        }

        private String calculateGrade(double percentage) {
            if (percentage >= 90) return "A+";
            else if (percentage >= 80) return "A";
            else if (percentage >= 70) return "B";
            else if (percentage >= 60) return "C";
            else if (percentage >= 50) return "D";
            else return "F";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int numSubjects = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of subjects:"));
            StudentMarksCalculator calculator = new StudentMarksCalculator(numSubjects);
            calculator.setVisible(true);
        });
    }
}
