package principal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import produto.GerenciarProduto;
import produto.Produto;
import usuario.GerenciarUsuario;
import usuario.Usuario;

public class Principal {

	private Scanner lerTeclado;
	private final static int CADASTRAR = 1;
	private final static int EDITAR = 2;
	private final static int LISTAR = 3;
	private final static int REMOVER = 4;
	private final static int SAIR = 9;
	private final static int USUARIO = 1;
	private final static int PRODUTO = 2;
	private GerenciarUsuario gerUsuario;
	private GerenciarProduto gerProduto;

	public Principal() {
		lerTeclado = new Scanner(System.in);
		gerUsuario = new GerenciarUsuario();
		gerProduto = new GerenciarProduto();
	}

	public static void main(String[] args) {
		new Principal().executar();
	}

	private void executar() {
		int opcao = 0;
		
		do {
			mostrarMenu();

			opcao = lerTeclado.nextInt();

			if (opcao == USUARIO) {
				executarUsuario();
			} else if (opcao == PRODUTO) {
				executarProduto();
			} else if (opcao == SAIR) {
				gerUsuario.fechar();
			}else {
				System.out.println("!!ESSA OPÇÃO NÃO EXISTE");
			}

		} while (opcao != SAIR);
	}
	
	private void executarUsuario() {
		int opcao = 0;

		do {
			mostrarMenuUsuario();

			opcao = lerTeclado.nextInt();

			if (opcao == CADASTRAR) {
				System.out.println("Cadastrar usuário");
				cadastrarUsuario();
			} else if (opcao == EDITAR) {
				System.out.println("Editar usuário");
				editarUsuario();
			} else if (opcao == LISTAR) {
				System.out.println("Listar usuários");
				listarUsuario();
			} else if (opcao == REMOVER) {
				System.out.println("Remover usuário");
				removerUsuario();
			} else if (opcao == SAIR) {
				executar();
			}else {
				System.out.println("!!ESSA OPÇÃO NÃO EXISTE");
			}

		} while (opcao != SAIR);
	}
	
	private void executarProduto() {
		int opcao = 0;

		do {
			mostrarMenuProduto();

			opcao = lerTeclado.nextInt();

			if (opcao == CADASTRAR) {
				System.out.println("Cadastrar produto");
				cadastrarProduto();
			} else if (opcao == EDITAR) {
				System.out.println("Editar produto");
				editarProduto();
			} else if (opcao == LISTAR) {
				System.out.println("Listar produto");
				listarProduto();
			} else if (opcao == REMOVER) {
				System.out.println("Remover prdouto");
				removerProduto();
			} else if (opcao == SAIR) {
				executar();
			}else {
				System.out.println("!!ESSA OPÇÃO NÃO EXISTE");
			}

		} while (opcao != SAIR);
	}

	private void cadastrarUsuario() {
		System.out.println("Digite o nome: ");
		String nome = lerTeclado.next();

		System.out.println("Digite o e-mail: ");
		String email = lerTeclado.next();

		System.out.println("Digite a data de nascimento(dd/mm/aaaa): ");
		String dtNascimento = lerTeclado.next();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataNasc = LocalDate.parse(dtNascimento, dtf);
		Usuario user = new Usuario();

		user.setNome(nome);
		user.setEmail(email);
		user.setDataNascimento(dataNasc);

		gerUsuario.criar(user);
		System.out.println("Usuário cadastro com sucesso");
	}
	
	private void listarUsuario() {
		List<Usuario> lista = gerUsuario.listar();
		for (Usuario usuario : lista) {
			System.out.println("\nID: " + usuario.getId());
			System.out.println("Nome: " + usuario.getNome());
		}

	}
	
	private void editarUsuario() {
		System.out.println("Digite o ID para editar: ");
		int id = lerTeclado.nextInt();

		Usuario usuario = gerUsuario.findById(id);

		System.out.println("Digite o novo nome: ");
		String novoNome = lerTeclado.next();
		System.out.println("Digite o novo e-mail: ");
		String novoEmail = lerTeclado.next();
		usuario.setNome(novoNome);
		usuario.setEmail(novoEmail);
		gerUsuario.editar(usuario);
		System.out.println("Usuário atualizado com sucesso");
	}

	private void removerUsuario() {
		System.out.println("Digite o id para remover: ");
		int idRemover = lerTeclado.nextInt();
		Usuario usuario = gerUsuario.findById(idRemover);
		if (usuario == null) {
			System.out.println("ID informado não existe");
		} else {
			System.out.println("ID: " + usuario.getId());
			System.out.println("Nome: " + usuario.getNome());
			System.out.println("E-mail: " + usuario.getEmail());

			System.out.println("Confirma a exclusão: 0 - sim, 1 - não");
			int opcao = lerTeclado.nextInt();

			if (opcao == 0) {
				gerUsuario.remover(usuario);
				System.out.println("OK, removido com sucesso");
			} else {
				System.out.println("Cancelar");
			}

		}
	}
	
	private void cadastrarProduto() {
		System.out.println("Digite o nome: ");
		String nome = lerTeclado.next();

		System.out.println("Digite a marca: ");
		String marca = lerTeclado.next();
		
		System.out.println("Digite o preco do produto:  ");
		BigDecimal preco = lerTeclado.nextBigDecimal();

		System.out.println("Digite a data de fabricação(dd/mm/aaaa): ");
		String dtFab = lerTeclado.next();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataFab = LocalDate.parse(dtFab, dtf);
		Produto produto = new Produto();

		produto.setNome(nome);
		produto.setMarca(marca);
		produto.setDataFabricacao(dataFab);
		produto.setPreco(preco);

		gerProduto.criar(produto);
		System.out.println("Produto cadastro com sucesso");
	}
	
	private void listarProduto() {
		List<Produto> lista = gerProduto.listar();
		for (Produto produto : lista) {
			System.out.println("\nID: " + produto.getId());
			System.out.println("Nome: " + produto.getNome());
		}

	}
	
	private void editarProduto() {
		System.out.println("Digite o ID para editar: ");
		int id = lerTeclado.nextInt();

		Produto produto = gerProduto.findById(id);

		System.out.println("Digite o novo nome: ");
		String novoNome = lerTeclado.next();
		System.out.println("Digite a nova marca: ");
		String novaMarca = lerTeclado.next();
		System.out.println("Digite o novo proço:  ");
		BigDecimal novoPreco = lerTeclado.nextBigDecimal();
		produto.setNome(novoNome);
		produto.setMarca(novaMarca);
		produto.setPreco(novoPreco);
		gerProduto.editar(produto);
		System.out.println("Usuário atualizado com sucesso");
	}

	private void removerProduto() {
		System.out.println("Digite o id para remover: ");
		int idRemover = lerTeclado.nextInt();
		Produto produto = gerProduto.findById(idRemover);
		if (produto == null) {
			System.out.println("ID informado não existe");
		} else {
			System.out.println("ID: " + produto.getId());
			System.out.println("Nome: " + produto.getNome());
			System.out.println("Marca: " + produto.getMarca());

			System.out.println("Confirma a exclusão: 0 - sim, 1 - não");
			int opcao = lerTeclado.nextInt();

			if (opcao == 0) {
				gerProduto.remover(produto);
				System.out.println("OK, removido com sucesso");
			} else {
				System.out.println("Cancelar");
			}

		}
	}

	private void mostrarMenu() {
		System.out.println("\n------ SUPER CRUD ------\n");
		System.out.println("O que deseja gerenciar?");
		System.out.println("1 - Usuarios");
		System.out.println("2 - Produtos");
		System.out.println("9 - Sair");
	}
	
	private void mostrarMenuUsuario() {
		System.out.println("1 - Cadastrar usuário");
		System.out.println("2 - Editar usuário");
		System.out.println("3 - Listar usuários");
		System.out.println("4 - Remover usuário");
		System.out.println("9 - Sair");
	}
	
	private void mostrarMenuProduto() {
		System.out.println("1 - Cadastrar produto");
		System.out.println("2 - Editar produto");
		System.out.println("3 - Listar produto");
		System.out.println("4 - Remover produto");
		System.out.println("9 - Sair");
	}

}