package br.imd.pgm.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.imd.pgm.controller.DocPdfBean;

@WebServlet(value = "/oficioResposta.pdf")
public class ServletOficioRespostaPdf extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DocPdfBean docPdfBean;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Long id = Long.parseLong(request.getParameter("codigo"));
		
		byte[] bytes = this.docPdfBean.lerRespostaPdf(id);
		
		if(bytes != null && bytes.length > 0){
			response.setContentType("application/pdf");
			ServletOutputStream saida = response.getOutputStream();
			saida.write(bytes, 0, bytes.length);
			saida.flush();
			saida.close();
		}
	}

}
