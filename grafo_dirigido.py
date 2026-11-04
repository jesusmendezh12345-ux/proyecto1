import random
from collections import deque


class Grafo:
    def __init__(self):
        self.adyacencia = {}

    def agregar_vertice(self, v):
        if v not in self.adyacencia:
            self.adyacencia[v] = []

    def agregar_arista(self, origen, destino, prob=1.0):
        self.agregar_vertice(origen)
        self.agregar_vertice(destino)
        self.adyacencia[origen].append((destino, prob))

    def mostrar(self):
        for v in self.adyacencia:
            vecinos = [d for d, p in self.adyacencia[v]]
            print(v, "->", vecinos)

    def propagacion_bfs(self, inicio):
        visitados = {inicio: 0}
        cola = deque([inicio])
        while cola:
            actual = cola.popleft()
            for vecino, p in self.adyacencia[actual]:
                if vecino not in visitados:
                    visitados[vecino] = visitados[actual] + 1
                    cola.append(vecino)
        return visitados

    def propagacion_dfs(self, inicio):
        visitados = []
        pila = [inicio]
        while pila:
            actual = pila.pop()
            if actual not in visitados:
                visitados.append(actual)
                for vecino, p in reversed(self.adyacencia[actual]):
                    if vecino not in visitados:
                        pila.append(vecino)
        return visitados

    def propagacion_probabilistica(self, inicio):
        infectados = {inicio: 0}
        nuevos = [inicio]
        paso = 0
        while nuevos:
            paso += 1
            siguientes = []
            for v in nuevos:
                for vecino, prob in self.adyacencia[v]:
                    if vecino not in infectados and random.random() < prob:
                        infectados[vecino] = paso
                        siguientes.append(vecino)
            nuevos = siguientes
        return infectados

    def promedio_alcance(self, inicio, repeticiones=1000):
        total = 0
        for i in range(repeticiones):
            total += len(self.propagacion_probabilistica(inicio))
        return total / repeticiones


if __name__ == "__main__":
    g = Grafo()
    g.agregar_arista("A", "B", 0.8)
    g.agregar_arista("A", "C", 0.5)
    g.agregar_arista("B", "D", 0.6)
    g.agregar_arista("C", "D", 0.4)
    g.agregar_arista("C", "E", 0.7)
    g.agregar_arista("D", "F", 0.9)
    g.agregar_arista("E", "F", 0.3)
    g.agregar_vertice("G")

    print("Grafo:")
    g.mostrar()

    print("\nPropagacion BFS desde A:")
    for v, nivel in g.propagacion_bfs("A").items():
        print("Nivel", nivel, ":", v)

    print("\nPropagacion DFS desde A:")
    print(g.propagacion_dfs("A"))

    print("\nPropagacion probabilistica desde A:")
    for v, paso in g.propagacion_probabilistica("A").items():
        print("Paso", paso, ":", v)

    print("\nPromedio de nodos alcanzados:", g.promedio_alcance("A"))
