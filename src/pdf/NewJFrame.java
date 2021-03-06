/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.sun.pdfview.*;


/**
 *
 * @author jayala
 */
public class NewJFrame extends javax.swing.JFrame {
    PagePanel panelpdf;
	JFileChooser selector;
	PDFFile pdffile;
	int indice=0;
	
	public NewJFrame(){
		panelpdf=new PagePanel();
		JMenuBar barra=new JMenuBar();
		JMenu archivo=new JMenu("Archivo");
		JMenuItem ver=new JMenuItem("Buscar Archivo");
		ver.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				indice=0;
				selector=new JFileChooser();
				int op=selector.showOpenDialog(NewJFrame.this);
				if(op==JFileChooser.APPROVE_OPTION){
					try{
					File file = selector.getSelectedFile();
			        RandomAccessFile raf = new RandomAccessFile(file, "r");
			        FileChannel channel = raf.getChannel();
			        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY,0, channel.size());
			        pdffile = new PDFFile(buf);
			        PDFPage page = pdffile.getPage(indice);
			        panelpdf.showPage(page);
			        repaint();
					}catch(IOException ioe){
						JOptionPane.showMessageDialog(null, "Error al abrir el archivo");
					}
				}
			}
			
		});
		JPanel pabajo=new JPanel();
		JButton bsiguiente=new JButton("Siguiente");
		bsiguiente.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				indice++;
				PDFPage page = pdffile.getPage(indice);
			    panelpdf.showPage(page);
			}
			
		});
		JButton banterior=new JButton("Anterior");
		banterior.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				indice--;
				PDFPage page = pdffile.getPage(indice);
			    panelpdf.showPage(page);
			}
			
		});
		pabajo.add(banterior);
		pabajo.add(bsiguiente);
		archivo.add(ver);
		barra.add(archivo);
		setJMenuBar(barra);
		add(panelpdf);
		add(pabajo,BorderLayout.SOUTH);
                
                initComponents();
	}

    /**
     * Creates new form NewJFrame
 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        NewJFrame p=new NewJFrame();
        p.setDefaultCloseOperation(EXIT_ON_CLOSE);
	p.setVisible(true);
	p.setBounds(0, 0, 500, 500);
	p.setLocationRelativeTo(null);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
