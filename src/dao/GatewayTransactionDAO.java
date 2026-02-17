package dao;

import model.GatewayTransaction;
import util.DBConnection;
import java.sql.*;

public class GatewayTransactionDAO {

    public void saveGatewayTxn(GatewayTransaction txn) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO payment_gateway_transactions(account_id,gateway_name,amount,status,transaction_ref) VALUES(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, txn.getAccountId());
        ps.setString(2, txn.getGatewayName());
        ps.setDouble(3, txn.getAmount());
        ps.setString(4, txn.getStatus());
        ps.setString(5, txn.getReference());

        ps.executeUpdate();
        con.close();
    }
}
