package com.kiselev.wiselyapp.database;

import com.kiselev.wiselyapp.database.dao.Spend_CommentDAO;
import com.kiselev.wiselyapp.database.dao.Spend_IncomeDAO;
import com.kiselev.wiselyapp.database.dao.Spend_TypeDAO;
import com.kiselev.wiselyapp.database.dao.TypeDAO;
import com.kiselev.wiselyapp.database.entity.Spend_Comment;
import com.kiselev.wiselyapp.database.entity.Spend_Income;
import com.kiselev.wiselyapp.database.entity.Spend_Type;
import com.kiselev.wiselyapp.database.entity.Type;

public class TestDataset {
    Spend_IncomeDAO spend_incomeDAO;
    Spend_TypeDAO spend_typeDAO;
    Spend_CommentDAO spend_commentDAO;
    TypeDAO typeDAO;



    public TestDataset() {
        AppDatabase db = DBHelper.getInstance().getDatabase();
        this.spend_incomeDAO = db.spend_incomeDAO();
        this.spend_typeDAO = db.spend_typeDAO();
        this.spend_commentDAO = db.spend_commentDAO();
        this.typeDAO = db.typeDAO();
    }

    public void insertTestDataSetInDB(){
        ///////////////  Апрель  //////////////////
        addSpend(234.2, "1/3/2021", type_name[1]);
        addSpend(342.3, "1/3/2021", type_name[2]);

        addSpend(256.9, "2/3/2021", type_name[4], "Джинсы");
        addSpend(211, "2/3/2021", type_name[5]);

        addSpend(412, "3/3/2021", type_name[1], "Корм кошке");
        addSpend(2223, "3/3/2021", type_name[1]);
        addSpend(235, "3/3/2021", type_name[1]);

        addSpend(450, "4/3/2021", type_name[3], "Мегафон связь");
        addSpend(243, "4/3/2021", type_name[7], "Купил компот");

        addSpend(577, "5/3/2021", type_name[8], "Квартплата");
        addSpend(75, "5/3/2021", type_name[8], "Оплатил домофон");

        addSpend(653, "6/3/2021", type_name[1]);
        addSpend(350, "6/3/2021", type_name[7]);

        addSpend(450, "7/3/2021", type_name[3], "Оплата интернета");
        addSpend(346.5, "7/3/2021", type_name[1]);

        addSpend(676, "8/3/2021", type_name[4]);
        addSpend(586, "8/3/2021", type_name[4]);

        addSpend(253, "9/3/2021", type_name[5]);
        addSpend(342.5, "9/3/2021", type_name[1], "Лента");
        addSpend(523, "9/3/2021", type_name[5]);

        addSpend(787, "11/3/2021", type_name[0], "Цветы");
        addSpend(897, "11/3/2021", type_name[1], "Суши");

        addSpend(122, "13/3/2021", type_name[1], "Мария-ра");
        addSpend(423, "13/3/2021", type_name[1]);

        addSpend(324, "14/3/2021", type_name[5]);
        addSpend(235, "14/3/2021", type_name[7]);

        addSpend(124, "15/3/2021", type_name[5]);
        addSpend(423, "15/3/2021", type_name[6]);
        addSpend(524, "15/3/2021", type_name[6]);
        addSpend(87, "15/3/2021", type_name[6]);

        addSpend(123, "16/3/2021", type_name[5]);
        addSpend(423, "16/3/2021", type_name[6], "Кино");
        addSpend(356, "16/3/2021", type_name[6]);
        addSpend(23, "16/3/2021", type_name[6]);

        addSpend(134, "17/3/2021", type_name[1]);
        addSpend(234, "17/3/2021", type_name[1]);
        addSpend(345, "17/3/2021", type_name[1]);

        addSpend(543, "20/3/2021", type_name[5]);
        addSpend(123, "20/3/2021", type_name[1], "Кока-кола");

        addSpend(423, "21/3/2021", type_name[1]);

        addSpend(234, "22/3/2021", type_name[1], "Лента");
        addSpend(132, "22/3/2021", type_name[6], "Кино");

        addSpend(223, "23/3/2021", type_name[1]);

        addSpend(476, "24/3/2021", type_name[5]);
        addSpend(234, "24/3/2021", type_name[1]);
        addSpend(12, "24/3/2021", type_name[7]);

        addSpend(654, "25/3/2021", type_name[1]);

        addSpend(235, "26/3/2021", type_name[1], "Пятёрочка");
        addSpend(455, "26/3/2021", type_name[1], "Лента");

        addSpend(321, "27/3/2021", type_name[1], "Монетка");

        addSpend(234, "28/3/2021", type_name[5]);
        addSpend(2314, "28/3/2021", type_name[4]);

        addSpend(876, "29/3/2021", type_name[4]);

        addSpend(234, "30/3/2021", type_name[6], "Кино");
        addSpend(345, "30/3/2021", type_name[6], "Аттракционы");


        addIncome(345, "6/3/2021");
        addIncome(250, "10/3/2021");
        addIncome(450, "12/3/2021");
        addIncome(14999, "5/3/2021");
        addIncome(15001, "19/3/2021");
        addIncome(5002, "25/3/2021");

        ///////////////  Май  //////////////////

        addSpend(234.2, "1/4/2021", type_name[1]);
        addSpend(135.3, "1/4/2021", type_name[2]);

        addSpend(999.9, "2/4/2021", type_name[4], "Джинсы");
        addSpend(211, "2/4/2021", type_name[5]);

        addSpend(1200, "3/4/2021", type_name[1], "Корм кошке");
        addSpend(1232, "3/4/2021", type_name[1]);
        addSpend(1232, "3/4/2021", type_name[1]);

        addSpend(450, "4/4/2021", type_name[3], "Мегафон связь");
        addSpend(350, "4/4/2021", type_name[7], "Купил симпл-димпл");

        addSpend(870, "5/4/2021", type_name[8], "Квартплата");
        addSpend(75, "5/4/2021", type_name[8], "Оплатил домофон");

        addSpend(653, "6/4/2021", type_name[1]);
        addSpend(431, "6/4/2021", type_name[2], "купил лекарства");
        addSpend(350, "6/4/2021", type_name[7]);

        addSpend(450, "7/4/2021", type_name[3], "Оплата интернета");
        addSpend(653.5, "7/4/2021", type_name[1]);

        addSpend(1200, "8/4/2021", type_name[4]);

        addSpend(195, "9/4/2021", type_name[5]);
        addSpend(306.4, "9/4/2021", type_name[1], "Лента");
        addSpend(191, "9/4/2021", type_name[5]);

        addSpend(900, "11/4/2021", type_name[0], "Цветы");
        addSpend(1000, "11/4/2021", type_name[1], "Суши");

        addSpend(460, "13/4/2021", type_name[1], "Мария-ра");
        addSpend(130, "13/4/2021", type_name[1]);

        addSpend(185, "14/4/2021", type_name[5]);
        addSpend(360, "14/4/2021", type_name[7]);

        addSpend(185, "15/4/2021", type_name[5]);
        addSpend(300, "15/4/2021", type_name[6]);
        addSpend(120, "15/4/2021", type_name[6]);
        addSpend(80, "15/4/2021", type_name[6]);

        addSpend(123, "16/4/2021", type_name[5]);
        addSpend(334, "16/4/2021", type_name[6], "Кино");
        addSpend(111, "16/4/2021", type_name[6]);
        addSpend(40, "16/4/2021", type_name[6]);

        addSpend(700, "17/4/2021", type_name[1]);
        addSpend(100, "17/4/2021", type_name[1]);
        addSpend(213, "17/4/2021", type_name[1]);

        addSpend(184, "20/4/2021", type_name[5]);
        addSpend(120, "20/4/2021", type_name[1], "Кока-кола");

        addSpend(450, "22/4/2021", type_name[1], "Лента");
        addSpend(310, "22/4/2021", type_name[6], "Кино");

        addSpend(169, "24/4/2021", type_name[5]);
        addSpend(210, "24/4/2021", type_name[1]);
        addSpend(75, "24/4/2021", type_name[7]);

        addSpend(215, "26/4/2021", type_name[1], "Пятёрочка");
        addSpend(332, "26/4/2021", type_name[1], "Лента");

        addSpend(193, "28/4/2021", type_name[5]);
        addSpend(1400, "28/4/2021", type_name[4]);

        addSpend(400, "30/4/2021", type_name[6], "Кино");
        addSpend(350, "30/4/2021", type_name[6], "Аттракционы");

        addSpend(196, "31/4/2021", type_name[5]);

        addIncome(14999, "5/4/2021");
        addIncome(15001, "19/4/2021");
        addIncome(5002, "25/4/2021");

        ///////////////  Июнь  //////////////////

        addSpend(300, "1/5/2021", type_name[1]);
        addSpend(140, "1/5/2021", type_name[1]);
        addSpend(160, "1/5/2021", type_name[1]);

        addSpend(140, "2/5/2021", type_name[5]);
        addSpend(140, "2/5/2021", type_name[6]);
        addSpend(60, "2/5/2021", type_name[6]);
        addSpend(30, "2/5/2021", type_name[6]);

        addSpend(393, "3/5/2021", type_name[1]);
        addSpend(130, "3/5/2021", type_name[1]);

        addSpend(450, "4/5/2021", type_name[3], "Мегафон связь");
        addSpend(350, "4/5/2021", type_name[7], "Купил поп-ит");

        addSpend(870, "5/5/2021", type_name[8], "Квартплата");
        addSpend(75, "5/5/2021", type_name[8], "Оплатил домофон");

        addIncome(14999, "5/5/2021");





    }

    String[] type_name = {
            "Остальное",    //0
            "Продукты",     //1
            "Здоровье",     //2
            "Связь и Интернет", //3
            "Одежда",       //4
            "Транспорт",    //5
            "Досуг",        //6
            "Интернет-магазины", //7
            "Счета ЖКХ"     //8
    };

    public void addSpend(double amount, String date, String type_name){
        Spend_Income spend_income = new Spend_Income(amount, date, 0);
        spend_incomeDAO.insert(spend_income);
        spendAndType(spend_incomeDAO.getLastPrimaryKey(), type_name);
    }

    public void addSpend(double amount, String date, String type_name, String comment){
        Spend_Income spend_income = new Spend_Income(amount, date, 0);
        spend_incomeDAO.insert(spend_income);
        spendAndType(spend_incomeDAO.getLastPrimaryKey(), type_name);
        spendAndComment(spend_incomeDAO.getLastPrimaryKey(), comment);
    }

    public void spendAndType(int spend_id, String type_name){
        Spend_Type spend_type = new Spend_Type(spend_id, typeDAO.getIdType(type_name));
        spend_typeDAO.insert(spend_type);
    }

    public void spendAndComment(int spend_id, String comment){
        Spend_Comment spend_comment = new Spend_Comment(spend_id, comment);
        spend_commentDAO.insert(spend_comment);
    }

    public void addIncome(double amount, String date){
        Spend_Income spend_income = new Spend_Income(amount, date, 1);
        spend_incomeDAO.insert(spend_income);
    }
}
