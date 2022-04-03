package com.ailois.table_saw;

@SuppressWarnings("all")
public class TableSaw {

    public static void main(String[] args) {

        String path = "/Users/biyancheng/Downloads/个人代码/idea/MyProject1/src/main/resources/2021_2_2013_48_25.csv";
//        Table usersTable = Table.read().csv(path);
//        System.out.println(usersTable);
//        Row row = usersTable.stream().findFirst().orElseGet(null);
//        int i = row.columnCount();
//        System.out.println(i);
        /* *
         * row 转 pojo 类
         */
//        List<Users> users = usersTable.stream().map(Users::form).collect(Collectors.toList());
//        System.out.println(users);

//        usersTable.clear();
//        System.out.println(usersTable.structure());
//
//        Table rows = usersTable.emptyCopy();



//        addRows(users, rows);
//
//        System.out.println(rows);



    }

//    public static void addRows(List<Users> users, Table table) {
//        users.forEach(user -> {
//            Row row = new Row(table);
//            List<String> strings = row.columnNames();
//            strings.forEach(x -> x);
//            row.setInt("id", user.getId());
//            row.setString("store", user.getStore());
//            row.setString("name", user.getName());
//            row.setString("email", user.getEmail());
//            row.setInt("email_verified", user.getEmailVerified());
//            row.setInt("is_anonymous", user.getIsAnonymous());
//            row.setInt("ip", user.getIp());
//            row.setDate("created_at", LocalDate.parse(user.getStore()));
//            row.setDate("updated_at", LocalDate.parse(user.getStore()));
//            table.addRow(row);
//        });
//    }

}
