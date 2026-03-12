package exercise;

import java.util.Scanner;

public class TrainingManagement {

    TraineeForm traineeForm;
    Scanner scanner = new Scanner(System.in);

    Trainee[] listOfTrainees = new Trainee[100];
    int count = 0;

    public TrainingManagement() {
        traineeForm = new TraineeForm(scanner);
    }

    public void addTrainee() {

        System.out.print("Enter number of trainees: ");
        int n = Integer.parseInt(scanner.nextLine());

        for(int i = 0; i < n; i++) {

            System.out.println("Enter trainee " + (i + 1));

            String id = traineeForm.getId();

            Trainee t = traineeForm.getTrainee();
            t.setId(id);

            listOfTrainees[count] = t;
            count++;
        }
    }

    public void displayAllTrainees() {

        for(int i = 0; i < count; i++) {

            Trainee t = listOfTrainees[i];

            System.out.println(
                    t.getId() + " | " +
                    t.getName() + " | " +
                    t.getGender() + " | " +
                    t.getAge()
            );
        }
    }

    public Trainee findTraineeById(String id) {

        for(int i = 0; i < count; i++) {

            if(listOfTrainees[i].getId().equals(id)) {
                return listOfTrainees[i];
            }
        }

        return null;
    }

    public static void main(String[] args) {

    TrainingManagement tm = new TrainingManagement();
    Scanner sc = new Scanner(System.in);

    while(true) {

        System.out.println("1. Add trainee");
        System.out.println("2. Display all");
        System.out.println("3. Find by id");
        System.out.println("4. Exit");

        int choice = Integer.parseInt(sc.nextLine());

        switch(choice) {

            case 1:
                tm.addTrainee();
                break;

            case 2:
                tm.displayAllTrainees();
                break;

            case 3:
                System.out.print("Enter id: ");
                String id = sc.nextLine();

                Trainee t = tm.findTraineeById(id);

                if(t != null)
                    System.out.println(t.getName());
                else
                    System.out.println("Not found");

                break;

            case 4:
                return;
        }
    }
}
}