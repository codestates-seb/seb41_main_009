import styled from 'styled-components';
import { LabelListTitle, LabelXSmall } from '../../../styles/typo';
import InputCard from '../../molecules/InputCard';
import { TextButton } from '../Buttons';

export const Box = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 20px 0;
`;

export const Label = styled.div`
  width: 100%;
  margin-bottom: 5px;
  ${LabelListTitle}
`;

export const CheckButton = styled(TextButton)`
  ${LabelXSmall};
`;

export const SignupInput = ({ type, placeholder, onChange, message, asideInput }) => {
  return (
    <InputCard
      width="512px"
      height="90px"
      boxShadow="var(--boxShadow-00) black"
      type={'' || type}
      placeholder={placeholder}
      inputWidth="100%"
      inputHeight="30px"
      onChange={onChange}
      asideInput={asideInput}
      message={message}
      messageColor="red"
    />
  );
};
