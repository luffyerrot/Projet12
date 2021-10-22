import { EnterpriseInfos } from "./enterprise-info";

export interface Enterprise {
	id: string;
	name: string;
	email: string;
	linkimg: string;
	password: string;
	enterpriseInformations: EnterpriseInfos;
}
