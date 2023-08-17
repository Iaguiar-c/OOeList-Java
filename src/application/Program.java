package application;

import entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Employee> list = new ArrayList<>();

        System.out.print("How many employees will be registered? ");
        int N = sc.nextInt();

        for (int i=0; i<N; i++) {

            System.out.println("Employee #" + (i +1) + ":");
            System.out.println("Id: ");
            Integer id = sc.nextInt();
            while (hasId(list, id)) {
                System.out.println("ID already taken! Try again: ");
                id = sc.nextInt();
            }

            System.out.println("Name: ");
            sc.nextLine(); //limpar buffer de entrada
            String name = sc.nextLine();
            System.out.println("Salary: ");
            Double salary = sc.nextDouble();

            //instanciando (criando um obj) dentro da memoria um novo obj employee
            Employee emp = new Employee(id, name, salary);

            //inserindo o obj na lista
            list.add(emp);

        }

        System.out.println();
        System.out.println("Enter the employee id that will have salary increase: ");
        int idsalary = sc.nextInt();
        Integer pos = position(list, idsalary);
        if (pos == null) {
            System.out.println(" This id does not exist! " );
        }
        else {
            System.out.print("Enter the porcentage: ");
            double percent = sc.nextDouble();
            list.get(pos).increaseSalary(percent);
        }

        System.out.println();
        System.out.println("List of employees: ");
        for (Employee emp : list) {
            System.out.println(emp  );
        }

        sc.close();
    }


    //dizer qual a posição do id na lista
    public static Integer position(List<Employee> list, int id) {
        for (int i=0; i<list.size(); i++) {
            if(list.get(i).getId() == id){
                return i;
            }
        }
        return null;
    }

    public static boolean hasId(List<Employee> list, int id) {
        Employee emp = list.stream().filter(x->x.getId() == id).findFirst().orElse(null);
        return  emp != null;
    }
}
