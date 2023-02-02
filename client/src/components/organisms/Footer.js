import styled from 'styled-components';
import { LabelMedium } from '../../styles/typo';
import { TextButton } from '../atoms/Buttons';

const Container = styled.div`
  width: auto;
  height: fit-content;
  background-color: var(--gray-700);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 50px;
  /* margin-top: 500px; */
  z-index: 99;
`;

const Body = styled.div`
  display: flex;
  width: 100%;
  justify-content: space-between;
  align-items: center;
`;

const Logo = styled.div`
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 700;
  font-size: 36px;
  line-height: 52px;
  /* identical to box height, or 144% */

  /* primary/primaryB2 */

  color: #efefef;
`;
const RowList = styled.div`
  width: 100%;

  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 0px;
`;

const RowListFitContent = styled.div`
  display: flex;
  flex-direction: row;
  width: fit-content;
  align-items: center;
  justify-content: space-between;
`;

const ColumnList = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  align-items: flex-end;
  justify-content: space-between;
`;

const ColumnList2 = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  align-items: flex-start;
  margin-left: 400px;
  justify-content: space-between;
`;
const OuterColumnList = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  align-items: flex-end;
  justify-content: space-between;
  gap: 20px;
`;

const UserButton = styled(TextButton)`
  width: fit-content;
  justify-content: center;
  color: var(--gray-100);
  margin-right: 30px;

  &:visited {
    color: var(--gray-200);
  }

  ${LabelMedium}
`;
const Label = styled(TextButton)`
  width: 120px;
  justify-content: center;
  color: var(--gray-100);
  margin-right: 20px;

  &:visited {
    color: var(--gray-200);
  }

  ${LabelMedium}
`;

const Border = styled.div`
  width: 100%;
  border: 1px solid #757575;
  margin-right: 15px;
`;

const Footer = () => {
  return (
    <Container>
      <Body>
        <OuterColumnList>
          <RowList>
            <ColumnList2>
              <RowListFitContent>
                <Label>client</Label>
                <UserButton
                  onClick={() =>
                    window.open('https://www.notion.so/variants00/Variants-9820752a46004fcdad852b24d94f10d9', '_blank')
                  }>
                  bakjonghyo52
                </UserButton>
                <UserButton onClick={() => window.open('https://github.com/atiakr', '_blank')}>atiakr</UserButton>
                <UserButton onClick={() => window.open('https://github.com/HJeong1200', '_blank')}>
                  HJeong1200
                </UserButton>
              </RowListFitContent>
              <RowListFitContent>
                <Label>server</Label>
                <UserButton onClick={() => window.open('https://github.com/cjswo4034', '_blank')}>cjswo4034</UserButton>
                <UserButton onClick={() => window.open('https://github.com/m0rethan', '_blank')}>m0rethan</UserButton>
                <UserButton onClick={() => window.open('https://github.com/gyuddi', '_blank')}>gyuddi</UserButton>
              </RowListFitContent>
            </ColumnList2>
            <ColumnList>
              <Label>Content Team</Label>
              <RowListFitContent>
                <UserButton
                  onClick={() => window.open('https://github.com/codestates-seb/seb41_main_009/tree/README', '_blank')}>
                  Github
                </UserButton>
                <UserButton
                  onClick={() =>
                    window.open('https://codestates.notion.site/8fe2c265d13545ad8c15bd4ea7923bbf', '_blank')
                  }>
                  Notion
                </UserButton>
              </RowListFitContent>
            </ColumnList>
          </RowList>
          <Border />
          <RowList>
            <Logo>IntoRest</Logo>
            <ColumnList>
              <UserButton>Move To</UserButton>
              <RowListFitContent>
                <UserButton to="/login">login</UserButton>
                <UserButton to="/signup">signup</UserButton>
                <UserButton to="/showcase/new">showcase</UserButton>
                <UserButton to="/posts">posts</UserButton>
                <UserButton to="/series">series</UserButton>
                <UserButton to="/users/1">users</UserButton>
                <UserButton to="/search/search">search</UserButton>
              </RowListFitContent>
            </ColumnList>
          </RowList>
        </OuterColumnList>
      </Body>
    </Container>
  );
};

export default Footer;
