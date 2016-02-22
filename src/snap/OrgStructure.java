package snap;

import java.io.File;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.*;

/**
 * Created by on 2/21/2016.
 */

//地里py大大面经里的print公司结构的问题。给一些data比如
//        Employee,Manager,ItemsSold
//        Alice,,5
//        Bob,Alice,3
//        Carol,Bob,2
//        Richard,Carol,5
//        Kim,Richard,5
//        Tom,Carol,5
//        David,Bob,3
//        Eve,Alice,2
//        Ferris,Eve,1
//        就是员工跟他的manager还有销售的item，之后输出下面的结构，数字表示该员工跟他下属的sold item总额
//        Alice 31
//        |-Bob 23
//        | |-Carol 17
//        | | |-Richard 10.鏈枃鍘熷垱鑷�1point3acres璁哄潧
//        | | | \_Kim 5
//        | | \_Tom 5. Waral 鍗氬鏈夋洿澶氭枃绔�,
//        | \_David 3
//        \_Eve 3
//        \_Ferris 1-go

public class OrgStructure {
    public void printStructure(String filePath) throws IOException {
        // parse input, and build organization tree
        Employee ceo = buildTree(filePath);

        // traverse the graph by depth first search from bottom to up, and sum item sold
        traverseAndSum(ceo);

        // print result
        printSum(ceo, 0);
    }

    private void printSum(Employee employee, int level) {
        if(employee == null) {
            return;
        }

        for(int i = 0; i < level; i++) {
            System.out.print(" | ");
        }

        System.out.print(employee.getName() + " ");
        System.out.print(employee.getTotalSold());
        System.out.print(System.lineSeparator());

        for(Employee directReport : employee.getDirectReports()) {
            printSum(directReport, level + 1);
        }
    }

    private void traverseAndSum(Employee employee) {
        if(employee == null) {
            return;
        }

        int sum = employee.getItemSold();
        for(Employee directReport : employee.getDirectReports()) {
            traverseAndSum(directReport);
            sum += directReport.getTotalSold();
        }

        employee.setTotalSold(sum);
    }

    private Employee buildTree(String filePath) throws  IOException {
        Map<String, Employee> map = new HashMap<>();
        Scanner scanner = new Scanner(new File(filePath));
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] strs = line.split(",");
            if(strs.length != 3) {
                throw new StreamCorruptedException(line + "is not valid!");
            }

            Employee manager = null;
            if(!strs[1].equals("")) {
                manager = map.get(strs[1]);
                if(manager == null) {
                    throw new StreamCorruptedException("Manager " + strs[1] + "does not exist yet!");
                }
            }

            if(map.containsKey(strs[0])) {
                throw new StreamCorruptedException(strs[0] + "already exists!");
            }

            Employee employee = new Employee(strs[0], manager, Integer.parseInt(strs[2]));
            map.put(strs[0], employee);
            if(manager != null) {
                manager.addDirectReport(employee);
            }
        }
        scanner.close();

        // find the top level employee, who has no manager
        for(Employee employee : map.values()) {
            if(employee.getManager() == null) {
                return employee;
            }
        }

        return null;
    }
}

// each employee can have zero or one manager, and zero or more direct reports
class Employee {
    private final String name;
    private final Employee manager;
    private final List<Employee> directReports;
    private final int itemSold;
    private int totalSold;

    public String getName() {
        return name;
    }

    public Employee getManager() {
        return manager;
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

    public int getItemSold() {
        return itemSold;
    }

    public int getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }

    public Employee(String name, Employee manager, int itemSold) {
        this.name = name;
        this.manager = manager;
        this.itemSold = itemSold;
        this.directReports = new ArrayList<>();
    }

    public void addDirectReport(Employee directReport) {
        directReports.add(directReport);
    }
}
