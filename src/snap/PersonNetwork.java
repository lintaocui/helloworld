package snap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
 Created by on 2/21/2016.
 一个Person类，让实现打出这个Person的关系网中的所有朋友，Person有属性id, name和friendList, sample输出是这样子的：
 * Mike(1) {Mitch, Ryan}
 * Mitch(2) {Mike}
 * Ryan(3) {Mike, Lila}
 * Lila(4) {Ryan}
 * Mike.printFriendsGraph() : Mitch, Ryan, Lila
 */

public class PersonNetwork  {
    private final Map<String, Person> map;
    public PersonNetwork(String filePath) {
        map = new HashMap<>();
        // build graph
        Scanner scanner = null;
        try {
             scanner = new Scanner(new File(filePath));
             while(scanner.hasNextLine()) {
                 String[] strs = scanner.nextLine().split(",\\s*");

             }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(scanner != null) {
                scanner.close();
            }
        }

    }

    public void printFriendsGraph(String name) {

    }
}


class Person {
    private final int id;
    private final String name;
    private final List<Person> friendList;

    public Person(int id, String name, List<Person> friendList) {
        this.id = id;
        this.name = name;
        this.friendList = friendList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Person> getFriendList() {
        return friendList;
    }

    public void addFriend(Person friend) {
        friendList.add(friend);
    }

//    public List<Person> findFriendsGraph() {
//        // bfs or dfs could do the work
//    }
}
