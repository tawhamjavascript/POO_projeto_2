package regras_negocio;
/**********************************
 * Projeto2 de POO (2022.2)
 * 
 * Grupo de alunos: 
 * fulano, beltrano e cicrano
 * 
 **********************************/
import modelo.Ingresso;
import modelo.IngressoGrupo;
import modelo.IngressoIndividual;
import modelo.Jogo;
import modelo.Time;
import java.util.Random;

import java.util.ArrayList;

import repositorio.Repositorio;

public class Fachada {
	private static Repositorio repositorio = new Repositorio();	
	private Fachada() {}	

	
	public static ArrayList<Time> listarTimes() {
		//retorna todos os times do reposit�rio
		return repositorio.getTimes();
	}
	public static ArrayList<Jogo> listarJogos() {
		//retorna todos os jogos do reposit�rio
		return repositorio.getJogos();

	}
	
	public static ArrayList<Ingresso> listarIngressos() {
		//retorna todos os ingressos do reposit�rio
		return repositorio.getIngressos();
	}
	public static ArrayList<Jogo> listarJogos(String data) {
		//retorna os jogos do reposit�rio na data fornecida
		//...
		ArrayList<Jogo> jogos = repositorio.getJogos();
		ArrayList<Jogo> aux = new ArrayList<Jogo>();
		for (Jogo jogo: jogos) {
			if (jogo.getData().equals(data)) aux.add(jogo);

		}
		return aux;
	}
	public static Ingresso localizarIngresso(int codigo) {
		//retorna o ingresso do reposit�rio com o c�digo fornecido
		//...
		return repositorio.localizarIngresso(codigo);
	}
	
	public static Jogo	localizarJogo(int id) {
		//retorna o jogo do reposit�rio com o id fornecido
		//...
		return repositorio.localizarJogo(id);
	}

	public static void criarTime(String nome, String origem) throws Exception {
		//Exce��o: nome existente no repositorio
		//criar o time
		//adicionar este time no reposit�rio
		//salvar o repositorio em arquivo
		Time time = repositorio.localizarTime(nome);
		if (time != null) {
			throw new Exception("Time já cadastrado");
		}
		repositorio.adicionar(new Time(nome, origem));
		repositorio.salvar();
	}

	public static void criarJogo(String data, String local, int estoque, double preco, String nometime1, String nometime2)  throws Exception {
		
		//Exce��o: nometime1 ou nometime2 inexistente no repositorio, 
		//  local ou data vazios, estoque ou pre�o menor ou igual a zero
		//gerar id sequencial do jogo 
		//criar o jogo, 
		//relacionar o jogo com os dois times 
		//adicionar este jogo no reposit�rio
		//salvar o repositorio em arquivo
		Time time1 = repositorio.localizarTime(nometime1);
		Time time2 = repositorio.localizarTime(nometime2);
		if (time1 == null || time2 == null) throw new Exception("Time inexistente");
		if (data.length() == 0) {
			throw new Exception("Data Inválida");
		}
		else if (local.length() == 0) {
			throw new Exception("Local inválida");
		}
		else if (estoque <= 0) { throw new Exception("Estoque inválido"); }
		else if (preco <= 0) { throw  new Exception("Preço inválido"); }
		Jogo jogo = new Jogo(repositorio.gerarId(), data, local, estoque, preco, time1, time2);
		repositorio.adicionar(jogo);
		time1.adicionar(jogo);
		time2.adicionar(jogo);
		repositorio.salvar();
	}
	
	public static void 	apagarJogo(int id) throws Exception {
		//Exce��o: id inexistente no repositorio
		//remover o jogo do reposit�rio
		//salvar o repositorio em arquivo
		Jogo jogo = repositorio.localizarJogo(id);
		if (jogo == null) {
			throw new Exception("Jogo inexistente");
		}
		repositorio.remover(jogo);
		repositorio.salvar();

	}

	public static void	criarIngressoIndividual(int id) throws Exception{
		//Exce��o: id inexistente no repositorio
		//gerar codigo aleat�rio e verificar unicididade do codigo no jogo indicado
		//criar o ingresso individual 
		//relacionar este ingresso com o jogo indicado
		//adicionar este ingresso no reposit�rio
		//salvar o repositorio em arquivo
		Jogo jogo = repositorio.localizarJogo(id);
		if (jogo == null) throw new Exception("Jogo Inexistente");
		Random sorteio = new Random();
		int codigo = sorteio.nextInt(1000000);
		while (jogo.has_ingresso(codigo)) {
			codigo = sorteio.nextInt(1000000);
		}
		IngressoIndividual ingresso = new IngressoIndividual(codigo);
		ingresso.setJogo(jogo);
		jogo.adicionar(ingresso);
		repositorio.adicionar(ingresso);
		repositorio.salvar();
	}
	
	public static void	criarIngressoGrupo(int[] id) throws Exception{
		//Exce��o: id inexistente no repositorio 
		//gerar codigo aleat�rio e verificar unicididade do codigo nos jogos indicados
		//criar o ingresso de grupo, 
		//relacionar este ingresso com os jogos indicados 
		//adicionar este ingresso no reposit�rio
		//salvar o repositorio em arquivo
		ArrayList<Jogo> jogos = new ArrayList<Jogo>();
		for (int codigo : id) {
			Jogo jogo = localizarJogo(codigo);
			if (jogo == null) throw new Exception("Jogo inexistente");
			jogos.add(jogo);
		}
		Random sorteio = new Random();
		boolean exist_ingresso = false;
		int codigo = 0;
		while (true) {
			codigo = sorteio.nextInt(1000000);
			for (Jogo jogo : jogos) {
				exist_ingresso = jogo.has_ingresso(codigo);
				if (exist_ingresso) {
					break;
				}
			}
			if (!exist_ingresso) break;
			codigo = sorteio.nextInt(1000000);
		}
		IngressoGrupo ingressoGrupo = new IngressoGrupo(codigo);
		for (Jogo jogo : jogos) {
			jogo.adicionar(ingressoGrupo);
			ingressoGrupo.adicionar(jogo);
		}
		repositorio.adicionar(ingressoGrupo);
		repositorio.salvar();

	}
	
	public static void	cancelarIngresso(int codigo)  throws Exception {
		//Exce��o: codigo inexistente no repositorio
		//remover o relacionamento entre ele e o(s) jogo(s) ligados a ele
		//remover ingresso do reposit�rio 
		//salvar o repositorio em arquivo
		Ingresso ingresso = repositorio.localizarIngresso(codigo);
		if (ingresso == null) {
			System.out.println("Ingresso inválido entrando");
			throw new Exception("Ingresso inválido");
		}

		if (ingresso instanceof IngressoIndividual ingressoIndividual) {
			Jogo jogo = ingressoIndividual.getJogo();
			ingressoIndividual.setJogo(null);
			jogo.remove(ingressoIndividual);

		}
		else if (ingresso instanceof IngressoGrupo ingressoGrupo) {
			ArrayList<Jogo> jogos = ingressoGrupo.getJogos();
			Jogo jogo = null;
			while (jogos.size() > 0) {
				jogo = jogos.get(0);
				jogo.remove(ingressoGrupo);
				ingressoGrupo.remove(jogo);

			}
		}
		repositorio.remover(ingresso);
		repositorio.salvar();
	}
}
