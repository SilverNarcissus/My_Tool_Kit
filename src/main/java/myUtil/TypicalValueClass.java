package myUtil;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by SilverNarcissus on 2016/12/6.
 */
public class TypicalValueClass implements Cloneable {

    private String name;
    private int age;
    private double salary;
    private List<String> childNames;

    public TypicalValueClass(String name, int age, double salary, List<String> childNames) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.childNames = childNames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<String> getChildNames() {
        return childNames;
    }

    public void setChildNames(ArrayList<String> childNames) {
        this.childNames = childNames;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(name);
        builder.append(age);
        builder.append(salary);
        builder.append(childNames);
        return builder.toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypicalValueClass)) {
            return false;
        }
        //
        TypicalValueClass another = (TypicalValueClass) o;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(name, another.getName());
        builder.append(age, another.getAge());
        builder.append(salary, another.getSalary());
        builder.append(childNames, another.childNames);
        return builder.isEquals();
    }

    public String formatString() {
        return String.format("age:%02d", age);
    }

    @Override
    public String toString() {
        String result="";
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                if (field.get(this) == null) {
                    result +=  "null" + "-";
                } else {
                    result +=  field.get(this).toString() + "-";
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return result.substring(0,result.length()-1);
    }
    public String print() {
        String lineSeparator = System.getProperty("line.separator", "\n");

        StringBuilder result = new StringBuilder();
        result.append("----------")
                .append(this.getClass().getName())
                .append("----------")
                .append(lineSeparator);
        //
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                result.append(field.getName());
                if (field.get(this) == null) {
                    result.append(": null    ");
                } else {
                    result.append(": ").append(field.get(this).toString()).append("    ");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        result.append(lineSeparator).append("--------------------").append(lineSeparator);

        return result.toString();
    }

    /**
     * 要谨慎使用clone，可以参考使用拷贝构造函数，静态拷贝工厂函数
     * @return 该类的克隆
     */
    @Override
    public TypicalValueClass clone() {
        TypicalValueClass result = null;
        try {
            result = (TypicalValueClass) super.clone();
            result.childNames=new ArrayList<String>(childNames);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 静态拷贝工厂，可以替代clone
     * @param another 需要被拷贝的类
     * @return 拷贝得到的类
     */
    public static TypicalValueClass copy(TypicalValueClass another){
        ArrayList<String> childNames=new ArrayList<String>(another.childNames);
        return new TypicalValueClass(another.name
                ,another.age
                ,another.salary
                ,childNames);
    }
}
