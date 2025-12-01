package br.edu.ifrs.poo2.prova.util;

import br.edu.ifrs.poo2.prova.dao.RotaDAO;
import br.edu.ifrs.poo2.prova.entity.PontoDeRota;
import br.edu.ifrs.poo2.prova.entity.Rota;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ImportadorGPX {

    public static void importar(File arquivo) throws Exception {
        // 1. Preparar o leitor de XML
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(arquivo);
        doc.getDocumentElement().normalize();

        // 2. Criar a Rota
        Rota novaRota = new Rota();
        novaRota.setNome(arquivo.getName()); // Usa o nome do arquivo como nome da rota
        novaRota.setDescricao("Importado de " + arquivo.getAbsolutePath());

        // 3. Ler todos os pontos (<trkpt>)
        NodeList listaPontos = doc.getElementsByTagName("trkpt");

        for (int i = 0; i < listaPontos.getLength(); i++) {
            Node node = listaPontos.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) node;

                // Ler atributos lat e lon
                String latStr = elemento.getAttribute("lat");
                String lonStr = elemento.getAttribute("lon");
                
                // Ler a elevação (<ele>) que fica DENTRO do trkpt
                String eleStr = "0";
                if (elemento.getElementsByTagName("ele").getLength() > 0) {
                    eleStr = elemento.getElementsByTagName("ele").item(0).getTextContent();
                }

                // Converter para double
                double lat = Double.parseDouble(latStr);
                double lon = Double.parseDouble(lonStr);
                double ele = Double.parseDouble(eleStr);

                // Criar o objeto e adicionar na rota
                PontoDeRota ponto = new PontoDeRota(lat, lon, ele);
                novaRota.adicionarPonto(ponto);
            }
        }

        // 4. Salvar no Banco
        RotaDAO dao = new RotaDAO();
        dao.salvar(novaRota);
        
        System.out.println("Rota importada com sucesso: " + novaRota.getNome() + " com " + novaRota.getPontos().size() + " pontos.");
    }
}