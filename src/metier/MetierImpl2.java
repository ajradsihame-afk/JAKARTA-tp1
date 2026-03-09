package metier;

import dao.IDao;

public class MetierImpl2 implements IMetier {
    private IDao dao;

    public void setDao(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        return dao.getValue() * 3;
    }
}