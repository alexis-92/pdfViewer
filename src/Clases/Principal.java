package Clases;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import javax.swing.*;
import com.sun.pdfview.*;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal extends JFrame {
	
	PagePanel panelpdf;
	JFileChooser selector;
	PDFFile pdffile;
	int indice=0;
        JPanel conta;
	
	public Principal(){
            conta= new JPanel();
		panelpdf=new PagePanel();
		JMenuBar barra=new JMenuBar();
		JMenu archivo=new JMenu("Archivo");
		JMenuItem ver=new JMenuItem("Buscar Archivo");
                conta.add(panelpdf);
		ver.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		JPanel pabajo=new JPanel();
                JButton bexa=new JButton("Examinar");
		bexa.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			indice=0;
				selector=new JFileChooser();
                                
				int op=selector.showOpenDialog(Principal.this);
				if(op==JFileChooser.APPROVE_OPTION){
					try{
                                            File file = selector.getSelectedFile();
                                          byte[] fileContent = Files.readAllBytes(file.toPath());
                                          String name="prueba";
                                          BufferedOutputStream bos = null;
                                            FileOutputStream fos = null;
                                            fos= new FileOutputStream(name);
                                            bos=new BufferedOutputStream(fos);
                                            bos.write(fileContent);  
			        RandomAccessFile raf = new RandomAccessFile(name, "r");
			        FileChannel channel = raf.getChannel();
			        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY,0, channel.size());
			        pdffile = new PDFFile(buf);
                                //conta.add(panelpdf, BorderLayout.CENTER);
			        PDFPage page = pdffile.getPage(indice);
			        panelpdf.showPage(page);
                                //panelpdf.
			        repaint();
                                
                                //conta.add(panelpdf);
                                
					}catch(IOException ioe){
						JOptionPane.showMessageDialog(null, "Error al abrir el archivo");
                                                System.out.print(ioe);
					} catch (Exception ex) {
                                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                    }
				}	
			}
			
		});
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
                pabajo.add(bexa);
		pabajo.add(banterior);
		pabajo.add(bsiguiente);
		archivo.add(ver);
		barra.add(archivo);
		setJMenuBar(barra);
		add(conta);
		add(pabajo,BorderLayout.SOUTH);
	}
        public static void printContent(File file) throws Exception {
        System.out.println("Print File Content");
        BufferedReader br = new BufferedReader(new FileReader(file));
 
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
 
        br.close();
    }
	
	public static void main(String arg[]){
		Principal p=new Principal();
		p.setDefaultCloseOperation(EXIT_ON_CLOSE);
		p.setVisible(true);
		p.setBounds(0, 0, 500, 500);
               // p.setBounds(WIDTH, WIDTH, WIDTH, HEIGHT);
		p.setLocationRelativeTo(null);
	}

}
