package exercise;

import java.util.Scanner;

public class StudentManager {

    Student[] students = new Student[100];
    int count = 0;

    Scanner sc = new Scanner(System.in);

        public void createStudent() {

            System.out.print("Enter number of students: ");
            int n = Integer.parseInt(sc.nextLine());

            for(int i = 0; i < n; i++) {

                System.out.println("Enter student " + (i + 1));

                Student st = new Student();

                System.out.print("Enter id: ");
                st.id = sc.nextLine();

                System.out.print("Enter name: ");
                st.name = sc.nextLine();

                System.out.print("Enter age: ");
                st.age = Integer.parseInt(sc.nextLine());

                System.out.print("Enter address: ");
                st.address = sc.nextLine();

                System.out.print("Enter gender: ");
                st.gender = sc.nextLine();

                System.out.print("Enter email: ");
                st.email = sc.nextLine();

                students[count] = st;
                count++;
            }
        }

        public void displayAll() {

            for(int i = 0; i < count; i++) {

                Student st = students[i];

                System.out.println(
                        st.id + " | " +
                        st.name + " | " +
                        st.age + " | " +
                        st.address + " | " +
                        st.gender + " | " +
                        st.email
                    );
                }
        }

        public void findStudent() {

            System.out.print("Enter id to find: ");
            String id = sc.nextLine();

            for(int i = 0; i < count; i++) {

                if(students[i].id.equals(id)) {

                    System.out.println("Found:");
                    System.out.println(students[i].name);

                    return;
                }

            }
            System.out.println("Student not found");
        }

        public void updateStudent() {

            System.out.print("Enter id to update: ");
            String id = sc.nextLine();

            for(int i = 0; i < count; i++) {

                if(students[i].id.equals(id)) {

                    System.out.print("New name: ");
                    students[i].name = sc.nextLine();

                    System.out.print("New age: ");
                    students[i].age = Integer.parseInt(sc.nextLine());

                    return;
                }
            }
            System.out.println("Student not found");
        }



}

