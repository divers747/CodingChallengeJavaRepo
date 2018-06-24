package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;

@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private ReportingStructureService RptStructSvc;

    @GetMapping("/rptstruct/{id}")
    public ReportingStructure read(@PathVariable String id) {
        LOG.debug("Received reporting structure request for id [{}]", id);

        return RptStructSvc.read(id);
    }

}
