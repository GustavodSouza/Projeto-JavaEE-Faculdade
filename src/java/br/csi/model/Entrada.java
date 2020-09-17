package br.csi.model;

public class Entrada {

    private int id;
    private int idProduto;
    private int idFuncionario;
    private String data_entrada;
    private int quantidade;

    public Entrada(int idProduto, int idFuncionario, String data_entrada, int quantidade) {
        this.idFuncionario = idFuncionario;
        this.idProduto = idProduto;
        this.data_entrada = data_entrada;
        this.quantidade = quantidade;
    }

    public Entrada() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(String data_entrada) {
        this.data_entrada = data_entrada;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
