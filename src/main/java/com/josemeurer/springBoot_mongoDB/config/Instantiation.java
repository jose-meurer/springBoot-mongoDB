package com.josemeurer.springBoot_mongoDB.config;

import com.josemeurer.springBoot_mongoDB.entities.Comment;
import com.josemeurer.springBoot_mongoDB.entities.Post;
import com.josemeurer.springBoot_mongoDB.entities.User;
import com.josemeurer.springBoot_mongoDB.entities.UserAuthor;
import com.josemeurer.springBoot_mongoDB.repositories.PostRepository;
import com.josemeurer.springBoot_mongoDB.repositories.UserRepository;
import com.josemeurer.springBoot_mongoDB.repositories.CommentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public Instantiation(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        UserAuthor mariaAuthor = new UserAuthor(maria.getId(), maria.getName());
        UserAuthor bobAuthor = new UserAuthor(bob.getId(), bob.getName());
        UserAuthor alexAuthor = new UserAuthor(alex.getId(), alex.getName());

        Post post1 = new Post(null, Instant.parse("2024-01-15T08:30:00Z"),
                "Explorando o Futuro da Tecnologia",
                "A tecnologia está evoluindo rapidamente, com inovações que prometem transformar nossas vidas. Neste post, vamos explorar algumas dessas tecnologias e seu impacto.",
                mariaAuthor);

        Post post2 = new Post(null, Instant.parse("2024-02-22T14:00:00Z"),
                "Receitas Saudáveis para o Dia a Dia",
                "Manter uma dieta equilibrada é essencial para a saúde. Confira algumas receitas práticas e saudáveis que você pode incorporar no seu dia a dia.",
                mariaAuthor);

        Post post3 = new Post(null, Instant.parse("2024-03-10T17:15:00Z"),
                "Viagem dos Sonhos: Os Melhores Destinos",
                "Se você está planejando sua próxima viagem, não pode deixar de conferir esses destinos incríveis que prometem experiências inesquecíveis.",
                alexAuthor);

        Post post4 = new Post(null, Instant.parse("2024-04-05T09:45:00Z"),
                "Desenvolvimento Pessoal: Dicas para Crescer na Carreira",
                "O desenvolvimento pessoal é fundamental para o sucesso profissional. Descubra algumas estratégias eficazes para crescer na sua carreira.",
                bobAuthor);

        Post post5 = new Post(null, Instant.parse("2024-05-18T11:30:00Z"),
                "Sustentabilidade: Como Fazer a Diferença",
                "A sustentabilidade é um tema cada vez mais importante. Veja como pequenas ações podem contribuir para um futuro mais sustentável.",
                bobAuthor);

        postRepository.saveAll(Arrays.asList(post1, post2, post3, post4, post5));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        alex.getPosts().add(post3);
        bob.getPosts().addAll(Arrays.asList(post4, post5));

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Comment comment1 = new Comment(null,
                Instant.parse("2024-01-15T09:32:00Z"), "Estou ansioso para ler mais posts como esse.", bobAuthor);
        Comment comment2 = new Comment(null,
                Instant.parse("2024-01-15T09:35:00Z"), "Excelente artigo! Aprendi muito com ele.", alexAuthor);

        Comment comment3 = new Comment(null,
                Instant.parse("2024-02-23T08:00:00Z"), "Muito bem escrito, gostei da forma como abordou o tema.", alexAuthor);
        Comment comment4 = new Comment(null,
                Instant.parse("2024-02-22T19:30:00Z"), "Estou ansioso para ler mais posts como esse.", bobAuthor);

        Comment comment5 = new Comment(null,
                Instant.parse("2024-03-10T17:55:00Z"), "Você tocou em pontos importantes que muitos ignoram. Obrigado!", mariaAuthor);
        Comment comment6 = new Comment(null,
                Instant.parse("2024-03-10T19:15:00Z"), "Ótimas dicas! Vou tentar aplicar na minha rotina.", bobAuthor);

        Comment comment7 = new Comment(null,
                Instant.parse("2024-04-05T11:45:00Z"), "Muito bem escrito, gostei da forma como abordou o tema.", mariaAuthor);
        Comment comment8 = new Comment(null,
                Instant.parse("2024-04-05T22:45:00Z"), "Estou ansioso para ler mais posts como esse.", alexAuthor);

        Comment comment9 = new Comment(null,
                Instant.parse("2024-05-18T11:38:00Z"), "Você tocou em pontos importantes que muitos ignoram. Obrigado!", alexAuthor);
        Comment comment10 = new Comment(null,
                Instant.parse("2024-05-18T11:42:00Z"), "Muito bem escrito, gostei da forma como abordou o tema.", mariaAuthor);

        commentRepository.saveAll(Arrays.asList(comment1, comment2, comment3,comment4, comment5, comment6, comment7, comment8, comment9, comment10));

        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post2.getComments().addAll(Arrays.asList(comment3, comment4));
        post3.getComments().addAll(Arrays.asList(comment5, comment6));
        post4.getComments().addAll(Arrays.asList(comment7, comment8));
        post5.getComments().addAll(Arrays.asList(comment9, comment10));

        postRepository.saveAll(Arrays.asList(post1, post2, post3, post4, post5));

    }
}
