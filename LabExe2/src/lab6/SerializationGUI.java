package lab6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class Student implements Serializable {
    private String name;
    private int age;
    private String course;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	

    // Constructors, getters, setters

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Course: " + course;
    }

	public Student(String name, int age, String course) {
		super();
		this.name = name;
		this.age = age;
		this.course = course;
	}

	public Student() {
		super();
	}
    
    
}

public class SerializationGUI extends JFrame {
    private JTextField nameField, ageField, courseField;
    private JTextArea outputTextArea;

    public SerializationGUI() {
        setTitle("Object Serialization");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI();
    }

    private void createUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2, 5, 5));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField();
        JLabel courseLabel = new JLabel("Course:");
        courseField = new JTextField();

        JButton clearButton = new JButton("Clear");
        JButton serializeButton = new JButton("Serialize");
        JButton deserializeButton = new JButton("Deserialize");

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        serializeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serializeObject();
            }
        });

        deserializeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deserializeObject();
            }
        });

        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(ageLabel);
        mainPanel.add(ageField);
        mainPanel.add(courseLabel);
        mainPanel.add(courseField);
        mainPanel.add(clearButton);
        mainPanel.add(serializeButton);
        mainPanel.add(deserializeButton);

        add(mainPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        courseField.setText("");
        nameField.requestFocus();
    }

    private void serializeObject() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("emp.txt"))) {
            Student student = new Student();
            student.setName(nameField.getText());
            student.setAge(Integer.parseInt(ageField.getText()));
            student.setCourse(courseField.getText());

            outputStream.writeObject(student);
            clearFields();
            outputTextArea.setText("Object Serialized Successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            outputTextArea.setText("Error during Serialization.");
        }
    }

    private void deserializeObject() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("emp.txt"))) {
            Student student = (Student) inputStream.readObject();
            outputTextArea.setText(student.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            outputTextArea.setText("Error during Deserialization.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SerializationGUI().setVisible(true);
            }
        });
    }
}
