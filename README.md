# 🗺️ JRouteViewer  
### Visualizador e Rastreador de Rotas GPX

Sistema desktop desenvolvido em **Java** para importação, persistência e visualização de trilhas GPS (formato `.gpx`) utilizando mapeamento **OpenStreetMap**.

Este projeto foi desenvolvido como **Avaliação Prática (Prova)** da disciplina de *Programação Orientada a Objetos II*.

---

![Screenshot do Sistema](screenshot_mapa.png)  
*(Tela principal exibindo uma rota importada com traçado no mapa e estatísticas)*

---

# 📋 Funcionalidades do Sistema

O software implementa integralmente todos os requisitos propostos na avaliação:

## 1️⃣ Importação de Arquivos GPX
- Leitura nativa de arquivos XML (`.gpx`) com `DocumentBuilder`.
- Extração de metadados e pontos de rastreamento:
  - Latitude  
  - Longitude  
  - Elevação  
- Persistência automática no banco de dados ao importar.
- Memorização do último diretório acessado pelo usuário.

## 2️⃣ Visualização Geoespacial (Mapas)
- Renderização interativa usando **JMapViewer** (OpenStreetMap).
- Desenho vetorial da rota com *polyline* destacada.
- **Controles de navegação:**
  - Zoom via scroll do mouse
  - Pan via botão direito
- **Auto-Zoom:** enquadra automaticamente toda a rota importada.

## 3️⃣ Estatísticas da Rota
- Cálculo automático e exibição de:
  - **Distância Total**  
    (método plano Euclidiano conforme especificação)
  - **Ganho de Elevação**
  - **Perda de Elevação**

## 4️⃣ Gerenciamento de Dados
- Listagem de todas as rotas salvas no banco.
- Visualização e reabertura de rotas anteriores.

---

# 🛠️ Tecnologias Utilizadas

| Componente       | Tecnologia |
|------------------|------------|
| Linguagem        | Java 17+ |
| Interface        | Java Swing (NetBeans/Matisse) |
| Look and Feel    | FlatLaf |
| ORM              | Hibernate 6.4 (JPA) |
| Banco de Dados   | H2 (modo arquivo) |
| Mapas            | JMapViewer 2.24 |
| Build/Projeto    | Maven |

---

# ⚙️ Instalação e Dependências (Modo Offline)

Devido a instabilidades nos repositórios públicos de bibliotecas do OpenStreetMap, o projeto utiliza dependência **local** para garantir compilação offline.

## 📁 Estrutura Obrigatória

A pasta do projeto **deve** conter a seguinte estrutura:

ProvaPoo2Mapas/
├── pom.xml
├── src/
│ └── ...
└── lib/ <-- PASTA OBRIGATÓRIA
└── jmapviewer-2.24.jar <-- ARQUIVO OBRIGATÓRIO

php-template
Copiar código

O `pom.xml` foi configurado com escopo `system` para utilizar esta dependência local:

## XML
<dependency>
    <groupId>org.openstreetmap.jmapviewer</groupId>
    <artifactId>jmapviewer</artifactId>
    <version>2.24</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/lib/jmapviewer-2.24.jar</systemPath>
</dependency>

## 🚀 Como Executar
1️⃣ Clonar o Repositório
Baixe o projeto para a sua máquina.

2️⃣ Verificar a Biblioteca
Confirme que a pasta lib contém o arquivo jmapviewer-2.24.jar.

3️⃣ Compilar o Projeto (Maven)
bash
Copiar código
mvn clean install
4️⃣ Executar a Aplicação
Rode a classe principal:

pgsql
Copiar código
br.edu.ifrs.poo2.prova.view.TelaMapa
📐 Modelagem de Dados (Hibernate)
O sistema utiliza mapeamento Objeto-Relacional com relacionamento @OneToMany e CascadeType.ALL.

## 🗂️ Rota (Pai)
Nome

Descrição

Lista de pontos

Relacionamento: uma rota possui vários PontoDeRota

## 📌 PontoDeRota (Filho)
Latitude

Longitude

Elevação

Referência à rota pai (ManyToOne)

O banco H2 é criado automaticamente na pasta:

bash
Copiar código
./dados/mapadb
na primeira execução.

### ✍️ Desenvolvido por
Maiki Scalvi
Instituto Federal de Educação, Ciência e Tecnologia do Rio Grande do Sul (IFRS) — Campus Veranópolis.
