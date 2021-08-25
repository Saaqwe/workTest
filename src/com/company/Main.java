package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Список всех текущих точек
        ArrayList<DotPair> dotsArr = new ArrayList<>();
        //Цикл для пользовательского ввода
        while (sc.hasNext()) {
            String inputLine =  sc.nextLine();
            String[] inputArr = inputLine.split(" ");
            String command = inputArr[0];

            switch (command) {
                // Добавление кординат
                case  ("add"):
                    for(int i = 1; i < inputArr.length - 1; i+=2) {
                        dotsArr.add(new DotPair(Integer.parseInt(inputArr[i]), Integer.parseInt(inputArr[i + 1])));
                    }
                    break;
                // Вывод групп
                case ("print"):
                    //Вспомогательный список точек, для промежуточный вычислений
                    //List<DotPair> dotsGroup = new ArrayList<>();
                    List<DotPair> dotsGroup = dotsArr;
                    // Вывод всех групп
                    if(inputArr.length == 1) {
                        System.out.print("Первая группа: ");
                        printGroupDots(dotsArr, 1);
                        System.out.print("Вторая группа: ");
                        printGroupDots(dotsArr, 2);
                        System.out.print("Третья группа: ");
                        printGroupDots(dotsArr, 3);
                        System.out.print("Количество точек, не вошедших ни в одну группу: ");
                        printGroupDots(dotsArr, 0);
                    } else {
                        // Вывод выбранных групп
                        for(int i = 1; i < inputArr.length; i++) {
                            dotsGroup = selectFromGroup(dotsGroup, Integer.parseInt(inputArr[i]));
                        }
                        //Вывод точек из выбранных групп
                        for(DotPair dots : dotsGroup) {
                            System.out.print(dots.toString() + " ");
                        }
                        if(dotsGroup.size() == 0) {
                            System.out.print("Эта группа пуста");
                        }
                        System.out.println();
                    }

                    break;
                // Удаление кордиат
                case ("remove"):
                    for(int i = 1; i < inputArr.length; i++) {
                        if(inputArr[i].equals("1")) {
                            dotsArr.removeIf(dotPair -> dotPair.inFirstGroup);
                        }
                        if(inputArr[i].equals("2")) {
                            dotsArr.removeIf(dotPair -> dotPair.inSecondGroup);
                        }
                        if(inputArr[i].equals("3")) {
                            dotsArr.removeIf(dotPair -> dotPair.inThirdGroup);
                        }
                    }
                    break;
                // Очищение всего списка точек
                case ("clear"):
                    dotsArr.clear();
                    break;
                // Вывод списка команд для вызова
                case ("help"):
              //      String addHelp = "add <point> - данная команда добавляет кодинаты парами, кординаты  записываются через" +
            //                " пробел, например: add 9 8 0 2, данная команда добавит 2 кординаты (9,8) и (0,2)";
            //        System.out.println(addHelp);
           //         String printHelp1 = "print - комманда выводит построчно все точки из всех групп, начиная с первой, " +
           //                 "в последней строке выводиться количество кординат не принадлежащих н одной группе";
          //          String printHelp2 = "print <group_num> - комманда выводит точки, которые входят в заданные в параметрах группы, пример команды: print 1 2";
          //          System.out.println(printHelp2);
                    String helpString = "add <point> - данная команда добавляет кодинаты парами, кординаты  записываются через " +
                                                "пробел, например: add 9 8 0 2, данная команда добавит 2 кординаты (9,8) и (0,2). \n" +
                                        "print - комманда выводит построчно все точки из всех групп, начиная с первой, в последней " +
                                                    "строке выводиться количество кординат не принадлежащих н одной группе. \n" +
                                        "print <group_num> - комманда выводит точки, которые входят в заданные в параметрах " +
                                                "группы, пример команды: print 1 2. \n" +
                                        "remove <group_num> - команда удаляет из памяти кординаты, которые содержаться " +
                                                "в переданных группах, пример команды: remove 1 2. \n" +
                                        "clear - команда стирает все точки из памяти.";

                    System.out.println(helpString);
                    //String removeHelp = "remove - ";
                    break;
                // Вывод сообщения в случаи неправельно введенной команды
                default:
                    System.out.println("Введенная команда не поддерживается, используйте команду \"help\" для получение" +
                            " информации о работающих командах");
                    break;
            }

        }
    }
    // Метод выводит кодрдинаты, принадлежащие заданной группе
    public static void printGroupDots(List<DotPair> dotsArr, int groupNum) {
        List<DotPair> dotsGroup = new ArrayList<>();
        if(groupNum == 0) {
            // Расчет точек не состоящих ни в дной группе
            dotsGroup = selectFromGroup(dotsArr, groupNum);
            System.out.println(dotsGroup.size());
            return;
        } else {
            dotsGroup = selectFromGroup(dotsArr, groupNum);
        }
        //Вывод в консоль всех точек
        for(DotPair dots : dotsGroup) {
            System.out.print(dots.toString() + " ");
        }
        if(dotsGroup.size() == 0) {
            System.out.print("Эта группа пуста");
        }
        System.out.println();
    }

    // Метод возвращает коллекцию объектов заданной группы
    public static List<DotPair> selectFromGroup(List<DotPair> dotsArr, int groupNum) {
        List<DotPair> dotsGroup = new ArrayList<>();
        if(groupNum == 1) {
            dotsGroup =  dotsArr.stream().filter(dotPair -> dotPair.inFirstGroup == true).collect(Collectors.toList());
        } else if(groupNum == 2) {
            dotsGroup =  dotsArr.stream().filter(dotPair -> dotPair.inSecondGroup == true).collect(Collectors.toList());
        } else if(groupNum == 3) {
            dotsGroup =  dotsArr.stream().filter(dotPair -> dotPair.inThirdGroup == true).collect(Collectors.toList());
        } else if(groupNum == 0) {
            dotsGroup =  dotsArr.stream().filter(dotPair -> dotPair.inFirstGroup == false && dotPair.inSecondGroup == false
                    && dotPair.inThirdGroup == false).collect(Collectors.toList());
        }
        return dotsGroup;
    }
}
