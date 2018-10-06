import pandas as pd
for i in range(1,32):
    number = str(i).zfill(2)
    data = pd.read_csv("/media/ding/other/滴滴云成都十月份/chengdu/gps_201610"+number,header=None)
    data = data.sort_values(by=2)
    data.to_csv("/media/ding/other/滴滴云成都十月份/chengdu2/gps_201610"+number,header=0,index=0)

    