package pres;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Presentation2 {
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Scanner sc=new Scanner(new File("config.txt"));
        String daoClassName=sc.nextLine();
        Class cDao = Class.forName(daoClassName);

        IDao dao=(IDao) cDao.getDeclaredConstructor().newInstance();

        String metierClassName=sc.nextLine();
        Class cMetier = Class.forName(metierClassName);
        IMetier metier = (IMetier) cMetier.getDeclaredConstructor().newInstance();
        Method method = cMetier.getMethod("setDao",IDao.class);
        method.invoke(metier,dao);
        System.out.println(metier.calcul());
    }
}
