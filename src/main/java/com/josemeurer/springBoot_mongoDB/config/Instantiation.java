package com.josemeurer.springBoot_mongoDB.config;

import com.josemeurer.springBoot_mongoDB.entities.AuthorPost;
import com.josemeurer.springBoot_mongoDB.entities.Post;
import com.josemeurer.springBoot_mongoDB.entities.User;
import com.josemeurer.springBoot_mongoDB.repositories.PostRepository;
import com.josemeurer.springBoot_mongoDB.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Instantiation(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, Instant.parse("2024-01-15T08:30:00Z"),
                "Explorando o Futuro da Tecnologia",
                "A tecnologia está evoluindo rapidamente, com inovações que prometem transformar nossas vidas. Neste post, vamos explorar algumas dessas tecnologias e seu impacto.",
                new AuthorPost(maria.getId(), maria.getName()));

        Post post2 = new Post(null, Instant.parse("2024-02-22T14:00:00Z"),
                "Receitas Saudáveis para o Dia a Dia",
                "Manter uma dieta equilibrada é essencial para a saúde. Confira algumas receitas práticas e saudáveis que você pode incorporar no seu dia a dia.",
                new AuthorPost(maria.getId(), maria.getName()));

        Post post3 = new Post(null, Instant.parse("2024-03-10T17:15:00Z"),
                "Viagem dos Sonhos: Os Melhores Destinos",
                "Se você está planejando sua próxima viagem, não pode deixar de conferir esses destinos incríveis que prometem experiências inesquecíveis.",
                new AuthorPost(alex.getId(), alex.getName()));

        Post post4 = new Post(null, Instant.parse("2024-04-05T09:45:00Z"),
                "Desenvolvimento Pessoal: Dicas para Crescer na Carreira",
                "O desenvolvimento pessoal é fundamental para o sucesso profissional. Descubra algumas estratégias eficazes para crescer na sua carreira.",
                new AuthorPost(bob.getId(), bob.getName()));

        Post post5 = new Post(null, Instant.parse("2024-05-18T11:30:00Z"),
                "Sustentabilidade: Como Fazer a Diferença",
                "A sustentabilidade é um tema cada vez mais importante. Veja como pequenas ações podem contribuir para um futuro mais sustentável.",
                new AuthorPost(bob.getId(), bob.getName()));

        postRepository.saveAll(Arrays.asList(post1, post2, post3, post4, post5));
    }
}
