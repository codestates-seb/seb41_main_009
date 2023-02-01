import { PASSWORD_NOT_MATCH } from '../../../constants/Messages';
import { Box, Label, SignupInput } from '../../atoms/signup/SignupComponents';

const PasswordCheckBox = ({ password, setPasswordCheck, passwordCheckMessage, setPasswordCheckMessage }) => {
  const onPasswordCheckInput = e => {
    const passwordCheckValue = e.target.value;

    setPasswordCheck(passwordCheckValue);

    if (password === passwordCheckValue || passwordCheckValue.length === 0) {
      setPasswordCheckMessage('');
    } else {
      setPasswordCheckMessage(PASSWORD_NOT_MATCH);
    }
  };

  return (
    <Box>
      <Label>Password Check</Label>
      <SignupInput
        type="password"
        placeholder="Re-Enter Your Password"
        onChange={onPasswordCheckInput}
        message={passwordCheckMessage}
      />
    </Box>
  );
};

export default PasswordCheckBox;
