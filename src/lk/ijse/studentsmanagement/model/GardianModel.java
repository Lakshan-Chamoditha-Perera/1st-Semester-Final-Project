package lk.ijse.studentsmanagement.model;

import lk.ijse.studentsmanagement.db.DBconnection;
import lk.ijse.studentsmanagement.to.Gardian;
import lk.ijse.studentsmanagement.util.CrudUtil;

import javax.sound.midi.Soundbank;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GardianModel {
    public static Gardian getGardianDetail(Gardian gardian) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM gardian where id = ?", gardian.getId());
        if(resultSet.next()){
           return new Gardian(
                   resultSet.getString(1),
                   resultSet.getString(2),
                   resultSet.getString(3),
                   resultSet.getString(4),
                   resultSet.getString(5),
                   resultSet.getString(6),
                   resultSet.getString(7)
           );
        }
        return null;
    }

    public static boolean addGardianT(Gardian gardian) throws SQLException, ClassNotFoundException {
        try {
            DBconnection.getInstance().getConnection().setAutoCommit(false);
            boolean flag = CrudUtil.execute("INSERT INTO gardian VALUES(?,?,?,?,?,?,?)",
                    gardian.getId(),
                    gardian.getName(),
                    gardian.getAddress(),
                    gardian.getMobile(),
                    gardian.getEmail(),
                    gardian.getDesignation(),
                    gardian.getWorkingPlace()
            );
            if(flag){
                boolean flag2 = RegistrationModel.addRegistration(gardian.getRegistration());
                if(flag2){
                    boolean flag3 = PaymentModel.addPayment(gardian.getRegistration().getPayment());
                    if(flag3){

                       DBconnection.getInstance().getConnection().commit();
                       System.out.println("Done");
                       return true;
                   }
               }
            }

            System.out.println("\n RollBack \n");
            DBconnection.getInstance().getConnection().rollback();
            return false;
        }finally {
            DBconnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
}
