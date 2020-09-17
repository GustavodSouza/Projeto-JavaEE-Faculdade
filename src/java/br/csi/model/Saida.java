package br.csi.model;

public class Saida {

    private int id;
    private int idFuncionario;
    private int idProduto;
    private String data_saida;
    private int quantidade;

    public Saida(int idProduto,int idFuncionario, String data_saida, int quantidade) {
        this.idFuncionario = idFuncionario;
        this.idProduto = idProduto;
        this.data_saida = data_saida;
        this.quantidade = quantidade;
    }

    public Saida() {
        
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

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getData_saida() {
        return data_saida;
    }

    public void setData_saida(String data_saida) {
        this.data_saida = data_saida;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
