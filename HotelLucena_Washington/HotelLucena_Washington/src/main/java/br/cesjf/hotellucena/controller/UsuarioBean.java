/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.hotellucena.controller;

import br.cesjf.hotellucena.dao.UsuarioDAO;
import br.cesjf.hotellucena.model.Usuario;
import java.util.ArrayList;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
@ManagedBean(name = "usuariosBean")
@ViewScoped
public class UsuarioBean {

    Usuario usuario = new Usuario();

    List usuarios = new ArrayList();

    //construtor
    public UsuarioBean() {
        usuarios = new UsuarioDAO().buscarTodas();
        usuario = new Usuario();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        new UsuarioDAO().persistir(usuario);
        usuarios = new UsuarioDAO().buscarTodas();
        usuario = new Usuario();
    }

    public void exclude(ActionEvent actionEvent) {
        new UsuarioDAO().remover(usuario);
        usuarios = new UsuarioDAO().buscarTodas();
        usuario = new Usuario();
    }
    
    public void login() throws IOException {
        Usuario usuarioLogado = new UsuarioDAO().login(this.usuario);
        
        if (usuarioLogado != null)
        {
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
            session.setAttribute("usuario", usuarioLogado);
            
            response.sendRedirect("/HotelLucena");
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Login incorreto!",
							"Favor inserir email novamente!"));
        }
    }
    
    public void logout() throws IOException {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        session.invalidate();
        response.sendRedirect("/HotelLucena/faces/login.xhtml");
    }
    
    //getters and setters
    public Usuario getUsuarios() {
        return usuario;
    }

    public void setUsuarios(Usuario usuario) {
        this.usuario = usuario;
    }

    public List getUsuarioss() {
        return usuarios;
    }

    public void setUsuarioss(List usuarios) {
        this.usuarios = usuarios;
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
}
