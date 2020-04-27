/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.hotellucena.controller;

import br.cesjf.hotellucena.dao.ReservaDAO;
import br.cesjf.hotellucena.model.Reserva;
import java.util.ArrayList;
import javax.faces.event.ActionEvent;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;

/**
 *
 * @author tassio
 */
@ManagedBean(name = "consultaReservasBean")
@SessionScoped
public class ConsultaReservaBean {

    Reserva reserva = new Reserva();

    List reservas = new ArrayList();

    //construtor
    public ConsultaReservaBean() {
        reservas = new ReservaDAO().buscarAtivos();
        reserva = new Reserva();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        Reserva r = new Reserva();
        Duration duracao = Duration.between(reserva.getDataEntrada().toInstant(), reserva.getDataSaida().toInstant());
        /*reservas = new ReservaDAO().buscarAtivos();
        for (Object ap : reservas) {
            Reserva res = (Reserva) ap;
            Duration resagenda = Duration.between(res.getDataEntrada().toInstant(), res.getDataSaida().toInstant());
            if (res.getApartamentocodigoApartamento().getIdApartamento().equals(reserva.getApartamentocodigoApartamento().getIdApartamento()) && duracao.equals(resagenda)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Quarto inválida nesta data"));
            } else
         */
        if (!duracao.isNegative() && !duracao.isZero()) {
            Double valor = r.camaExtra(reserva);
            if (valor != 0.0) {
                reserva.setValorPago(valor);
                new ReservaDAO().persistir(reserva);
                reservas = new ReservaDAO().buscarAtivos();
                reserva = new Reserva();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Quantidade de Hóspedes inválida"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Data selecionada inválida"));
        }
        //}
    }

    public void exclude(ActionEvent actionEvent) {
        new ReservaDAO().remover(reserva);
        reservas = new ReservaDAO().buscarAtivos();
        reserva = new Reserva();
    }

    public void checkin(ActionEvent actionEvent) {
        new ReservaDAO().checkin(reserva.getCodigoReserva());
        reservas = new ReservaDAO().buscarAtivos();
        reserva = new Reserva();
    }

    public void checkout(ActionEvent actionEvent) {
        new ReservaDAO().checkout(reserva.getCodigoReserva());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Check out", "Saída Concluída"));
        reservas = new ReservaDAO().buscarAtivos();
        reserva = new Reserva();
    }

    //getters and setters
    public Reserva getReservas() {
        return reserva;
    }

    public void setReservas(Reserva reserva) {
        this.reserva = reserva;
    }

    public List getReservass() {
        return reservas;
    }

    public void setReservass(List reservas) {
        this.reservas = reservas;
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);

            cell.setCellStyle(cellStyle);
        }
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        //String logo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" + File.separator + "images" + File.separator + "prime_logo.png";

        // pdf.add(Image.getInstance(logo));
    }

    public List<Reserva> buscarReservasUsuario(int id) {
        reservas = (List) new ReservaDAO().buscarReservas(id);
        return reservas;
    }

    public List<Reserva> buscarReservasApartamento(int id) {
        reservas = (List) new ReservaDAO().buscarReservasApartamento(id);
        return reservas;
    }
        
}
