from sklearn.cluster import KMeans
from sklearn.neighbors import KNeighborsClassifier 
from sklearn.neural_network import MLPClassifier
import pandas as pd
import numpy as np
import time
from sklearn import preprocessing
from sklearn.externals import joblib

mlp = MLPClassifier(solver='lbfgs', alpha=1e-5,hidden_layer_sizes=(50, 50), random_state=1)
mlp = joblib.load("mlp.pkl")
knn = joblib.load("knn.pkl")
def knnpre(lon,lat):
    return int(knn.predict([[lon,lat]])[0])
    
indexlist = []
for i in range(1,51):
    indexlist.append("4_"+str(i))
indexlist.sort()
m_max = 852
def mlppre(day1,day2,last1,israin,time,positiontype):
    data = pd.DataFrame([{0:day1/m_max,1:day2/m_max,2:last1/m_max,3:time/23,5:israin}])
    for i in indexlist:
        if "4_"+str(positiontype) == i:
            data[i] = 1
        else:
            data[i] = 0
    return mlp.predict(np.array(data))[0]


import logging
logging.basicConfig(level=logging.DEBUG)

from spyne import Application, rpc, ServiceBase,Integer, Unicode,Double
from spyne import Iterable
from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication

class HelloWorldService(ServiceBase):
    @rpc(Double, Double, _returns=Double)
    def myknnpre(self,lat,lon):
        return knnpre(lat,lon)
    
    @rpc(Double,Double,Double,Double,Double,Double,_returns=Double)
    def mymlppre(self,day1,day2,last1,israin,time,positiontype):
        return mlppre(day1,day2,last1,israin,time,positiontype)

application = Application([HelloWorldService],
    tns='spyne.examples.hello',
    in_protocol=Soap11(validator='lxml'),
    out_protocol=Soap11()
)

if __name__ == '__main__':
    # You can use any Wsgi server. Here, we chose
    # Python's built-in wsgi server but you're not
    # supposed to use it in production.
    from wsgiref.simple_server import make_server

    wsgi_app = WsgiApplication(application)
    server = make_server('0.0.0.0', 8000, wsgi_app)
    server.serve_forever()