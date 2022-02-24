package ext;

import dao.IDao;

public class DaoImpl2 implements IDao {
    @Override
    public double getData() {
        System.out.println("in Dao Implementation v2");
        return 0;
    }
}
