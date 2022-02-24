package dao;

import org.springframework.stereotype.Component;

@Component("dao")
public class DaoImpl implements IDao{
    @Override
    public double getData() {
        System.out.println("in Dao Implementation v1");
        return 0;
    }
}
