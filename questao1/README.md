# Questão 1 - Provisionamento Multinuvem

## Padrões aplicados

| Padrão | Papel GoF | Classe | Requisito |
|---|---|---|---|
| Abstract Factory | AbstractFactory | `ProvedorFactory` | RNF01, RNF02, RNF03, RNF06 |
| Abstract Factory | ConcreteFactory | `AwsFactory`, `AzureFactory` | RNF01, RNF03 |
| Abstract Factory | AbstractProduct | `Recurso`, `MaquinaVirtual`, `Armazenamento`, `Fila` | RNF01 |
| Abstract Factory | ConcreteProduct | `AwsMaquinaVirtual`, `AwsArmazenamento`, `AwsFila`, `AzureMaquinaVirtual`, `AzureArmazenamento`, `AzureFila` | RF01, RF02 |
| Abstract Factory | Client | `ProvisionadorAmbiente` | RNF02 |
| Singleton | Singleton | `Configuracao` | RNF04 |
| Singleton | Singleton | `HistoricoAuditoria` | RNF05 |

A `Main` é o único ponto que escolhe a fábrica concreta. Para incluir um novo provedor basta criar `GcpFactory` e seus três produtos e acrescentar o caso em `Main.escolherProvedor`.

## Compilar e executar

```bash
cd questao1
mkdir bin
javac -encoding UTF-8 -d bin $(find src -name "*.java")
java -cp "bin:src" Main
```

No Windows (PowerShell):

```powershell
cd questao1
mkdir bin
javac -encoding UTF-8 -d bin (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })
java -cp "bin;src" Main
```

O arquivo `src/config.properties` define a região padrão e o orçamento mensal máximo.

## Diagrama

`diagrama/questao1.drawio` (abrir em https://app.diagrams.net ou na extensão Draw.io do VS Code).
