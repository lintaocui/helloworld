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
             List<String[]> lines = new ArrayList<>();
             while(scanner.hasNextLine()) {
                lines.add(scanner.nextLine().split(",\\s*"));
             }
            scanner.close();

            // Initialize the graph
            for (String[] strs : lines) {
                map.put(strs[0], new Person(Integer.parseInt(strs[1]), strs[0]));
            }
            // add friend
            for (String[] strs : lines) {
                for(int i = 2; i < strs.length; i++) {
                    map.get(strs[0]).addFriend(map.get(strs[i]));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(scanner != null) {
                scanner.close();
            }
        }

    }

    // bfs print friend
    public void printFriendsGraph(String name) {
        if(!map.containsKey(name)) {
            return;
        }

        Set<String> visited = new HashSet<>();
        Deque<Person> queue = new ArrayDeque<>();
        queue.add(map.get(name));
        visited.add(name);
        while(!queue.isEmpty()) {
            Person person = queue.poll();
            System.out.print(person.getName() + " ");
            for (Person friend : person.getFriendList()) {
                if(visited.contains(friend.getName())){
                    continue;
                }

                queue.add(friend);
                visited.add(friend.getName());
            }
        }
    }
}

class Person {
    private final int id;
    private final String name;
    private final List<Person> friendList;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
        this.friendList = new ArrayList<>();
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
