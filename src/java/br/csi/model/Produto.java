package br.csi.model;

public class Produto {

    private int id;
    private int idFuncionario;
    private String descricao;
    private String marca;
    private float preco;

    public Produto(int idFuncionario, String descricao, String marca, float preco) {

        this.idFuncionario = idFuncionario;
        this.descricao = descricao;
        this.marca = marca;
        this.preco = preco;

    }

    public Produto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    
}
