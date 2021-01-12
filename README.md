# sky-movies-daniel-assumpcao

Projeto SkyMovies

O projeto SkyMovies foi desenvolvido com base na arquitetura MVP, onde a Activity faz o controle da lógica da tela e o Presenter faz a lógica de preparação dos dados.
Utilizando a biblioteca Retrofit para fazer a comunicação da IMDb API, modelei parcialmente a resposta do serviço, parseando apenas o que iria ser utilizado na minha solução. 
Utilizei também a biblioteca picasso para processamento de imagens.

Em relação a UX da solução, desenvolvi um item consistido da imagem do poster e do título do filme, apresentados em um grid de duas colunas, assim como exemplo mostrado no desafio.
Na tela principal, temos a funcionalidade de "pull to refresh" para fazer a atualização do dataset a partir do início.
Na tela de detalhe, optei por fazer uma tela de demonstração das informações do filme baseada nos principais aplicativos no mercado.

Em relação a integração da solução, optei por paginar o dataset em páginas de 4 itens devido a limitação de apenas 5 chamadas por segundo da api. 
Ao abrir o aplicativo pela primeira vez, é feita uma requisição ao endpoint "/title/get-most-popular-movies" afim de pegar os ids dos filmes a serem listados. 
Após isso, fazemos a requisição ao endpoint "/title/get-overview-details" com cada um dos ids dos filmes, limitados por página, afim de pegar os detalhes de cada filme para mostrá-los em tela.
Optei por não implementar uma persistência de dados visto de se tratar de um ranking que pode ser alterado a qualquer momento.
Para melhor entendimento do comportamento do usuário e possíveis erros gerados na experiência de uso, implementei uma instância do firebase, atendendo a melhoria contínua do aplicativo. 
