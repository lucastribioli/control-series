import com.google.gson.Gson;
import exceptions.DeuRuimEPorIssoDeuEssaExcecao;
import models.TituloOmdb;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
public class Busca {
    public static void main(String[] args) throws IOException, InterruptedException {
        
        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite um filme para busca: ");
        var busca = leitura.nextLine();

        String endereco = "https://omdbapi.com/?t="+ busca.replace(" ", "+") +"&apikey=352fe67e";
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            TituloOmdb meuTituloOmdb = gson.fromJson(response.body(), TituloOmdb.class);

            System.out.println(response.body());
            System.out.println(meuTituloOmdb.getTitle());
            try (FileWriter escrita = new FileWriter("filmes.txt", true)) {
                escrita.append("Nome do filme buscado: ").append(meuTituloOmdb.getTitle());
            }

        } catch (IOException e){
            System.out.println("Deu ruim" + e.getMessage());
            throw new DeuRuimEPorIssoDeuEssaExcecao("Essa exceção foi criada para testes apenas");
        }

    }

}
