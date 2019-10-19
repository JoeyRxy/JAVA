package mine.learn.annotation4j.sqlutils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * TableCreator
 * <p>
 * 注解处理器：读取一个类文件；检查其上的数据；并生成用来创建数据库的SQl命令
 * <p>
 * 使用<b>反射</b>技巧
 * 
 */
public class TableCreator {
    private static String getConstraints(Constraints con) {
        String constraints = "";
        if (!con.allowNull())
            constraints += " NOT NULL";
        if (con.primaryKey())
            constraints += " PRIMARY KEY";
        if (con.unique())
            constraints += " UNIQUE";
        return constraints;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        if (args.length < 1) {
            System.out.println("arguments : annotated classes");
            System.exit(0);
        }
        for (String clazzName : args) {
            Class<?> clazz = Class.forName(clazzName);
            DBTable dbTable = clazz.getAnnotation(DBTable.class);

            if (dbTable == null)
                System.out.println("Error:No DNTable annotations in class " + clazzName);

            String tableName = dbTable.name();
            // 如果name没有赋值，使用类名
            if (tableName.length() < 1)
                tableName = clazz.getName().toUpperCase();
            List<String> columnDefs = new ArrayList<>();

            for (Field field : clazz.getDeclaredFields()) {// TODO:foreach的iterator可以这样直接写吗？
                String columnName = null;
                Annotation[] annotations = field.getAnnotations();
                if (annotations.length < 1)
                    continue;// 不是数据库的列
                if (annotations[0] instanceof SqlInteger) {// ATTENTION：应该是默认都是第0号为类型注释（好像确实是的
                    SqlInteger sqlInteger = (SqlInteger) annotations[0];

                    // 如果没有指定名字，则使用属性名
                    if (sqlInteger.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sqlInteger.name();

                    columnDefs.add(columnName + " Int" + getConstraints(sqlInteger.constraints()));
                }

                if (annotations[0] instanceof SqlString) {
                    SqlString sString = (SqlString) annotations[0];
                    if (sString.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = field.getName().toUpperCase();
                    columnName = sString.name();
                    columnDefs.add(
                            columnName + " VARCHAR(" + sString.value() + ")" + getConstraints(sString.constraints()));
                }

                StringBuilder sqlCMD = new StringBuilder("CREATE TABlE " + tableName + "(");

                for (String columDef : columnDefs) {
                    sqlCMD.append("\n\t" + columDef + ",");
                }

                String tableCreate = sqlCMD.substring(0, sqlCMD.length() - 1) + ");";
                System.out.println("Table Craetino SQL for " + clazzName + " is :\n" + tableCreate);
            }
        }
    }

}