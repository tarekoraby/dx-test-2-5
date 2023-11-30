import {TaskOneAndTwoService} from 'Frontend/generated/endpoints.js';
import {AutoCrud} from "@hilla/react-crud";
import TeamDtoModel from "Frontend/generated/com/example/application/dtos/TeamDtoModel.js";

export default function TaskOneView() {

  return (
      <div>
        <h2>Task One</h2>
        <h4>Simple usage where all DTO fields are shown in Grid and form </h4>
        <br/>
        <AutoCrud service={TaskOneAndTwoService} model={TeamDtoModel} />
      </div>
  );
}
