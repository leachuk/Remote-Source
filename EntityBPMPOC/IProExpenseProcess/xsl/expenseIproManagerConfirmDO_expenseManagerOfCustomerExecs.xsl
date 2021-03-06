<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="XSD">
      <schema location="../businessCatalog/IProExpenses/ExpenseIProManagerConfirm.xsd"/>
      <rootElement name="ExpenseIProManagerConfirm" namespace="http://xmlns.oracle.com/bpm/bpmobject/IProExpenses/ExpenseIProManagerConfirm"/>
      <param name="expenseIproManagerConfirmDO" />
    </source>
    <source type="XSD">
      <schema location="../businessCatalog/IProExpenses/ExpenseIProManagerNotes.xsd"/>
      <rootElement name="ExpenseIProManagerNotes" namespace="http://xmlns.oracle.com/bpm/bpmobject/IProExpenses/ExpenseIProManagerNotes"/>
      <param name="expenseIproManagerNotesDO" />
    </source>
  </mapSources>
  <mapTargets>
    <target type="XSD">
      <schema location="../businessCatalog/IProExpenses/ExpenseManagerOfCustomerExecutives.xsd"/>
      <rootElement name="ExpenseManagerOfCustomerExecutives" namespace="http://xmlns.oracle.com/bpm/bpmobject/IProExpenses/ExpenseManagerOfCustomerExecutives"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.5.0(build 110418.1550.0174) AT [MON JUN 17 15:55:09 EST 2013]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:ns2="http://xmlns.oracle.com/bpm/bpmobject/IProExpenses/ExpenseManagerOfCustomerExecutives"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:bpel="http://docs.oasis-open.org/wsbpel/2.0/process/executable"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:med="http://schemas.oracle.com/mediator/xpath"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions"
                xmlns:bpmo="http://xmlns.oracle.com/bpm/bpmobject/"
                xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:ns1="http://xmlns.oracle.com/bpm/bpmobject/IProExpenses/ExpenseIProManagerNotes"
                xmlns:bpmn="http://schemas.oracle.com/bpm/xpath"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ns0="http://xmlns.oracle.com/bpm/bpmobject/IProExpenses/ExpenseIProManagerConfirm"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap"
                exclude-result-prefixes="xsi xsl bpmo xsd ns1 ns0 ns2 bpws xp20 mhdr bpel oraext dvm hwf med ids bpm xdk xref bpmn ora socket ldap">
  <xsl:param name="expenseIproManagerNotesDO"/>
  <xsl:template match="/">
    <ns2:ExpenseManagerOfCustomerExecutives>
      <ns2:amount>
        <xsl:value-of select="/ns0:ExpenseIProManagerConfirm/ns0:amount"/>
      </ns2:amount>
      <ns2:ipro_user>
        <xsl:value-of select="/ns0:ExpenseIProManagerConfirm/ns0:ipro_user"/>
      </ns2:ipro_user>
      <ns2:ipro_manager>
        <xsl:text disable-output-escaping="no">TODO IPro Manager name</xsl:text>
      </ns2:ipro_manager>
      <ns2:date_incured>
        <xsl:value-of select="/ns0:ExpenseIProManagerConfirm/ns0:date_incured"/>
      </ns2:date_incured>
    </ns2:ExpenseManagerOfCustomerExecutives>
  </xsl:template>
</xsl:stylesheet>
