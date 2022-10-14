package practice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
/**
 *
 * @author Миша Дунаев
 */
public class Practice {
    
    public static void main(String[] args) {
        
        try {
            
            String url = "jdbc:mysql://localhost:3306/phone?serverTimezone=Asia/Almaty&useSSL=false";
            Properties authorization = new Properties();
            authorization.put("user", "root");
            authorization.put("password", "root");
            Connection connection = DriverManager.getConnection(url, authorization);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet table = statement.executeQuery("SELECT * FROM student");
            System.out.println("Начальная таблица");
           
             table.first();
             for (int j = 1; j <= table.getMetaData().getColumnCount(); j++)
             {
                System.out.print(table.getMetaData().getColumnName(j) + "\t\t");
        }
             System.out.println();
              table.beforeFirst();
             while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++)
                {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();}
            System.out.println();
                Scanner sc = new Scanner(System.in);
                System.out.println("Введите параметры новой записи для таблицы данных:");
                System.out.print("Имя студента: ");
                String scannedName = sc.nextLine();
                System.out.print("Модель телефона: ");
                String scannedAuthor = sc.nextLine();
                System.out.println();
                System.out.println("После добавления строки:");
            statement.execute("INSERT student(name, phone) VALUES ('" + scannedName + "', '" + scannedAuthor + "')");
            table = statement.executeQuery("SELECT * FROM student");
            
            while (table.next()) 
            {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++)
                {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }
            System.out.println();
            
             System.out.println("Строку с каким id хотите удалить?");
            System.out.print("id: ");
            String scannedId = sc.nextLine();
            if (!scannedId.equals("")) 
            {
                statement.execute("DELETE FROM student WHERE id = " + scannedId);
            }
            System.out.println();
            
            System.out.println("Таблица после удаления записи:");
            table = statement.executeQuery("SELECT * FROM student");
            while (table.next())
            {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) 
                {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }
            System.out.println();
            
            System.out.println("Какую запись вы хотите изменить?");
            System.out.print("id: ");
            scannedId = sc.nextLine();
            System.out.println("Теперь вводите новые данные для данной записи");
            System.out.print("Имя студента: ");
            String scannedNameUp = sc.nextLine();
            System.out.print("Модель телефона: ");
            String scannedAuthorUp = sc.nextLine();
            if (!scannedId.equals(""))
            {
                statement.executeUpdate("UPDATE student SET name = '" + scannedNameUp + "' WHERE id = " + scannedId);
                statement.executeUpdate("UPDATE student SET phone = '" + scannedAuthorUp + "' WHERE id = " + scannedId);
            }
            System.out.println("Данные таблицы после изменения:");
            table = statement.executeQuery("SELECT * FROM student");
            System.out.println();
            
            System.out.print("Введите фрагмент названия для фильтрации: ");
            String filter = sc.nextLine();
            
            while (table.next()) 
            {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) 
                {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
                }
            
            System.out.println();
            
            System.out.println("Данные таблицы с фильтром и сортировкой:");
            table = statement.executeQuery("SELECT * FROM student WHERE name like '%"
                    + filter + "%' ORDER BY name DESC");
            
             while (table.next())
             {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++)
                {
                    System.out.print(table.getString(j) + "\t\t");
                }
                
                System.out.println();
            }
            
             
             while (table.next())
             {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) 
                {
                    System.out.print(table.getString(j) + "\t\t");
                }
                
             
                System.out.println();
             }
                
        
    if (table != null) { table.close(); }
    if (statement != null) { statement.close();}
    if (connection != null) { connection.close();}
        }
        catch (Exception e){
            System.err.println("Ошибка доступа к серверу или в вводе данных");
            e.printStackTrace();
        }
        
    }
}



        
    