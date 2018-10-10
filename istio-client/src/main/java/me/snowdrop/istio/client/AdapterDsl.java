package me.snowdrop.istio.client;

import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import me.snowdrop.istio.adapter.bypass.Bypass;
import me.snowdrop.istio.adapter.bypass.BypassList;
import me.snowdrop.istio.adapter.bypass.DoneableBypass;
import me.snowdrop.istio.adapter.circonus.Circonus;
import me.snowdrop.istio.adapter.circonus.CirconusList;
import me.snowdrop.istio.adapter.circonus.DoneableCirconus;
import me.snowdrop.istio.adapter.denier.Denier;
import me.snowdrop.istio.adapter.denier.DenierList;
import me.snowdrop.istio.adapter.denier.DoneableDenier;
import me.snowdrop.istio.adapter.fluentd.Fluentd;
import me.snowdrop.istio.adapter.fluentd.FluentdList;
import me.snowdrop.istio.adapter.fluentd.DoneableFluentd;
import me.snowdrop.istio.adapter.kubernetesenv.Kubernetesenv;
import me.snowdrop.istio.adapter.kubernetesenv.KubernetesenvList;
import me.snowdrop.istio.adapter.kubernetesenv.DoneableKubernetesenv;
import me.snowdrop.istio.adapter.memquota.Memquota;
import me.snowdrop.istio.adapter.memquota.MemquotaList;
import me.snowdrop.istio.adapter.memquota.DoneableMemquota;
import me.snowdrop.istio.adapter.opa.Opa;
import me.snowdrop.istio.adapter.opa.OpaList;
import me.snowdrop.istio.adapter.opa.DoneableOpa;
import me.snowdrop.istio.adapter.prometheus.Prometheus;
import me.snowdrop.istio.adapter.prometheus.PrometheusList;
import me.snowdrop.istio.adapter.prometheus.DoneablePrometheus;
import me.snowdrop.istio.adapter.servicecontrol.Servicecontrol;
import me.snowdrop.istio.adapter.servicecontrol.ServicecontrolList;
import me.snowdrop.istio.adapter.servicecontrol.DoneableServicecontrol;
import me.snowdrop.istio.adapter.solarwinds.Solarwinds;
import me.snowdrop.istio.adapter.solarwinds.SolarwindsList;
import me.snowdrop.istio.adapter.solarwinds.DoneableSolarwinds;
import me.snowdrop.istio.adapter.stackdriver.Stackdriver;
import me.snowdrop.istio.adapter.stackdriver.StackdriverList;
import me.snowdrop.istio.adapter.stackdriver.DoneableStackdriver;
import me.snowdrop.istio.adapter.statsd.Statsd;
import me.snowdrop.istio.adapter.statsd.StatsdList;
import me.snowdrop.istio.adapter.statsd.DoneableStatsd;
import me.snowdrop.istio.adapter.stdio.Stdio;
import me.snowdrop.istio.adapter.stdio.StdioList;
import me.snowdrop.istio.adapter.stdio.DoneableStdio;

public interface AdapterDsl {
  MixedOperation<Bypass,BypassList, DoneableBypass,Resource<Bypass,DoneableBypass>> bypass();
  MixedOperation<Circonus,CirconusList, DoneableCirconus,Resource<Circonus,DoneableCirconus>> circonus();
  MixedOperation<Denier,DenierList, DoneableDenier,Resource<Denier,DoneableDenier>> denier();
  MixedOperation<Fluentd,FluentdList, DoneableFluentd,Resource<Fluentd,DoneableFluentd>> fluentd();
  MixedOperation<Kubernetesenv,KubernetesenvList, DoneableKubernetesenv,Resource<Kubernetesenv,DoneableKubernetesenv>> kubernetesenv();
  MixedOperation<Memquota,MemquotaList, DoneableMemquota,Resource<Memquota,DoneableMemquota>> memquota();
  MixedOperation<Opa,OpaList, DoneableOpa,Resource<Opa,DoneableOpa>> opa();
  MixedOperation<Prometheus,PrometheusList, DoneablePrometheus,Resource<Prometheus,DoneablePrometheus>> prometheus();
  MixedOperation<Servicecontrol,ServicecontrolList, DoneableServicecontrol,Resource<Servicecontrol,DoneableServicecontrol>> servicecontrol();
  MixedOperation<Solarwinds,SolarwindsList, DoneableSolarwinds,Resource<Solarwinds,DoneableSolarwinds>> solarwinds();
  MixedOperation<Stackdriver,StackdriverList, DoneableStackdriver,Resource<Stackdriver,DoneableStackdriver>> stackdriver();
  MixedOperation<Statsd,StatsdList, DoneableStatsd,Resource<Statsd,DoneableStatsd>> statsd();
  MixedOperation<Stdio,StdioList, DoneableStdio,Resource<Stdio,DoneableStdio>> stdio();
}