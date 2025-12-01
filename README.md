# JRouteViewer

# 🗺️ JRouteViewer (Visualizador de Rotas GPX)

Sistema desktop desenvolvido em Java para importação, persistência e visualização de rotas de GPS (formato GPX) sobre mapas interativos do OpenStreetMap.

Este projeto foi desenvolvido como avaliação prática da disciplina de **Programação Orientada a Objetos II**.

!<img width="1024" height="583" alt="image" src="https://github.com/user-attachments/assets/631af6b5-76b3-4676-b26f-81afcf85931b" />


## 📋 Funcionalidades

O sistema atende aos 3 requisitos principais da avaliação:

### 1. Modelagem e Persistência (Hibernate + H2)
* Mapeamento Objeto-Relacional (ORM) das entidades:
    * **Rota:** Nome, descrição e lista de pontos.
    * **PontoDeRota:** Latitude, longitude e elevação.
* Relacionamento `@OneToMany` com persistência em cascata.
* Banco de dados **H2 (File Mode)**, garantindo que os dados persistam entre execuções.

### 2. Importação de Arquivos GPX
* Leitura nativa de arquivos XML (`.gpx`) utilizando `DocumentBuilder` (sem frameworks externos para o parse).
* Extração automática de metadados e coordenadas (`lat`, `lon`, `ele`).
* Persistência automática da rota e seus pontos no banco de dados após a importação.
* **JFileChooser:** Filtro para arquivos `.gpx` e persistência do último diretório acessado (usando `java.util.prefs`).

### 3. Visualização com JMapViewer
* Renderização interativa do mapa (OpenStreetMap).
* Desenho da rota utilizando `MapPolygon` (Polyline vermelha).
* **Zoom Automático:** A visualização se ajusta para enquadrar toda a rota (`setDisplayToFitMapPolygons`).
* **Estatísticas da Rota:** Cálculo e exibição de:
    * Distância Total (Cálculo Euclidiano Plano).
    * Ganho Total de Elevação.
    * Perda Total de Elevação.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17+
* **Interface Gráfica:** Java Swing (Desenhado com NetBeans/Matisse)
* **Gerenciador de Dependências:** Maven
* **ORM:** Hibernate (JPA)
* **Banco de Dados:** H2 Database (Embedded)
* **Mapas:** JMapViewer
* **UI/UX:** FlatLaf (Look and Feel moderno)

## ⚙️ Instalação e Dependências (Modo Offline)

Devido a instabilidades nos repositórios públicos do OpenStreetMap, a biblioteca **JMapViewer** foi configurada em modo **offline** para garantir a compilação e execução durante a prova.

### Estrutura de Pastas Necessária
Para que o Maven encontre a dependência, o projeto deve seguir esta estrutura:

```text
ProvaPoo2Mapas/
├── lib/
│   └── jmapviewer-2.24.jar  <-- ARQUIVO OBRIGATÓRIO
├── src/
└── pom.xml
