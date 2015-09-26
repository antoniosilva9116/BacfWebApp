/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence.Entities.Controllers;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author António Silva
 */
@Stateless
@LocalBean
@Named
public class DataSelected implements Serializable {

    private Date date;

    public DataSelected() {
        date = new Date();
    }

    public DataSelected(Date date) {
        this.date = date;
    }

    public Date getDate() {
        System.out.println("\n----------------GET DATE--------------\nDATA=" + date.toString());
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        if (date != null) {
            System.out.println("\n----------------SET DATE--------------\nDATA=" + date.toString());
            System.out.println("\n----------------SET DATE--------------\nDATA=" + this.date.toString());

        }
    }

}
